package gov.epa.ccte.api.hazard.web.rest;

import org.junit.jupiter.api.BeforeEach;

//This will test REST end-points in the GenetoxResource.java using WebMvcTest and MockitoBean

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;

import gov.epa.ccte.api.hazard.domain.GenetoxSummary;
import gov.epa.ccte.api.hazard.projection.CcdGenetoxDetail;
import gov.epa.ccte.api.hazard.repository.GenetoxDetailRepository;
import gov.epa.ccte.api.hazard.repository.GenetoxSummaryRepository;

import java.util.*;

@ActiveProfiles("test")
@WebMvcTest(GenetoxResource.class)
@RunWith(MockitoJUnitRunner.class)
class GenetoxResourceTest {
	
    @Autowired
    private MockMvc mockMvc;
    
    @MockitoBean
    private GenetoxDetailRepository genetoxDetailRepository;
    
    @MockitoBean
    private GenetoxSummaryRepository genetoxSummaryRepository;
    
    private CcdGenetoxDetail genetoxDetail;
    private GenetoxSummary genetoxSummary;
    private CcdGenetoxDetail ccdGenetoxDetail;
    private ProjectionFactory factory = new SpelAwareProxyProjectionFactory();
    
    @BeforeEach
    void setUp() {
    	//genetox detail is using a projection by default and does not display full domain
    	genetoxDetail = factory.createProjection(CcdGenetoxDetail.class);
    	genetoxDetail.setYear(null);
    	genetoxDetail.setSource("NTP");
    	genetoxDetail.setMetabolicActivation(null);
    	genetoxDetail.setDtxsid("DTXSID7020182");
    	genetoxDetail.setSpecies("Not specified");
    	genetoxDetail.setAssayResult("negative");
    	genetoxDetail.setAssayCategory("in vitro");
    	genetoxDetail.setAssayType("Ames");
    	genetoxDetail.setStrain(null);
    	
    	genetoxSummary = GenetoxSummary.builder()
    			.id(9861)
    			.ames("negative")
    			.clowderDocId("https://clowder.edap-cluster.com/files/680b9b14e4b096bca880a4c7")
    			.dtxsid("DTXSID7020182")
    			.genetoxCall("negative")
    			.genetoxSummaryId(9861)
    			.micronucleus("negative")
    			.reportsNegative(3)
    			.reportsOther(0)
    			.reportsPositive(0)
    			.build();
    	
    	//the difference between with/without specified projection is a concatinated String for 'assayType'
    	ccdGenetoxDetail = factory.createProjection(CcdGenetoxDetail.class);
    	ccdGenetoxDetail.setYear(null);
    	ccdGenetoxDetail.setSource("NTP");
    	ccdGenetoxDetail.setMetabolicActivation(null);
    	ccdGenetoxDetail.setDtxsid("DTXSID7020182");
    	ccdGenetoxDetail.setSpecies("Not specified");
    	ccdGenetoxDetail.setAssayResult("negative");
    	ccdGenetoxDetail.setAssayCategory("in vitro");
    	ccdGenetoxDetail.setAssayType("Ames | bacterial reverse mutation test");
    	ccdGenetoxDetail.setStrain(null);
    }
    
    
    @Test
    void testGetGenetoxSummaryByDtxsid() throws Exception {
        final List<GenetoxSummary> summary = Collections.singletonList(genetoxSummary);

        when(genetoxSummaryRepository.findByDtxsid("DTXSID7020182", GenetoxSummary.class)).thenReturn(summary);

        mockMvc.perform(get("/hazard/genetox/summary/search/by-dtxsid/{dtxsid}", "DTXSID7020182"))
				.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].dtxsid").value(genetoxSummary.getDtxsid()));
    
    }
    
    @Test
    void testGetGenetoxSummaryByBatchDtxsid() throws Exception {
    	final List<GenetoxSummary> summary = Collections.singletonList(genetoxSummary);
        String[] jsonArray = {"DTXSID7020182"};
        String jsonBody = new ObjectMapper().writeValueAsString(jsonArray);
        		
        when(genetoxSummaryRepository.findByDtxsidInOrderByDtxsidAsc(jsonArray, GenetoxSummary.class)).thenReturn(summary);
        
        
        mockMvc.perform(post("/hazard/genetox/summary/search/by-dtxsid/")
        		.accept(MediaType.APPLICATION_JSON)
        		.contentType(MediaType.APPLICATION_JSON)
        		.content(jsonBody))
        		.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].dtxsid").value(genetoxSummary.getDtxsid()))
                .andReturn();
	}
    
 // *********************** Summary - End *************************************

 // *********************** Detail - start *************************************


    @Test
    void testGetGenetoxDetailsByDtxsid() throws Exception {
        final List<CcdGenetoxDetail> details = Collections.singletonList(genetoxDetail);

        when(genetoxDetailRepository.findByDtxsidOrderBySourceAsc("DTXSID7020182", CcdGenetoxDetail.class)).thenReturn(details);

        mockMvc.perform(get("/hazard/genetox/details/search/by-dtxsid/{dtxsid}", "DTXSID7020182"))
				.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].dtxsid").value(genetoxDetail.getDtxsid()));
    
    }
    
    @Test
    void testGetGenetoxDetailsByDtxsidCCD() throws Exception {
        final List<CcdGenetoxDetail> details = Collections.singletonList(ccdGenetoxDetail);

        when(genetoxDetailRepository.findByDtxsidWithConcatenatedColumn("DTXSID7020182")).thenReturn(details);

        mockMvc.perform(get("/hazard/genetox/details/search/by-dtxsid/{dtxsid}", "DTXSID7020182")
        		.param("projection", "ccd-genetox-details"))
				.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].dtxsid").value(ccdGenetoxDetail.getDtxsid()))
                .andExpect(jsonPath("$[0].assayType").value(org.hamcrest.Matchers.containsString(" | ")));
    
    }
    
    @Test
    void testGenetoxDetailsByBatchDtxsid() throws Exception {
    	final List<CcdGenetoxDetail> details = Collections.singletonList(genetoxDetail);
        String[] jsonArray = {"DTXSID7020182"};
        String jsonBody = new ObjectMapper().writeValueAsString(jsonArray);
        		
        when(genetoxDetailRepository.findByDtxsidInOrderByDtxsidAsc(jsonArray, CcdGenetoxDetail.class)).thenReturn(details);
        
        
        mockMvc.perform(post("/hazard/genetox/details/search/by-dtxsid/")
        		.accept(MediaType.APPLICATION_JSON)
        		.contentType(MediaType.APPLICATION_JSON)
        		.content(jsonBody))
        		.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].dtxsid").value(genetoxDetail.getDtxsid()))
                .andReturn();
	}
}

package gov.epa.ccte.api.hazard.web.rest;

import org.junit.jupiter.api.BeforeEach;

//This will test REST end-points in the CancerSummaryResource.java using WebMvcTest and MockitoBean

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;

import gov.epa.ccte.api.hazard.domain.CancerSummary;
import gov.epa.ccte.api.hazard.repository.CancerSummaryRepository;

import java.util.*;

@ActiveProfiles("test")
@WebMvcTest(CancerSummaryResource.class)
@RunWith(MockitoJUnitRunner.class)
class CancerSummaryResourceTest {

    @Autowired
    private MockMvc mockMvc;
    
    @MockitoBean
    private CancerSummaryRepository cancerSummaryRepository;
    
    private CancerSummary cancerSummary;
    
    @BeforeEach
    void setUp() {
    	cancerSummary = CancerSummary.builder()
    			.id(1179)
    			.dtxsid("DTXSID9020112")
    			.source("IARC")
    			.exposureRoute("-")
    			.cancerCall("Group 3 - Not classifiable as to its carcinogenicity to humans")
    			.url("https://monographs.iarc.who.int/list-of-classifications/")
    			.build();
    }
    
    

    @Test
    void testGetCancerSummaryByDtxsid() throws Exception {
        final List<CancerSummary> summary = Collections.singletonList(cancerSummary);

        when(cancerSummaryRepository.findAllByDtxsid("DTXSID9020112", CancerSummary.class)).thenReturn(summary);

        mockMvc.perform(get("/hazard/cancer-summary/search/by-dtxsid/{dtxsid}", "DTXSID9020112"))
				.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].dtxsid").value(cancerSummary.getDtxsid()));
	}
    

    @Test
    void testCancerSummaryByBatchDtxsid() throws Exception {
    	final List<CancerSummary> summary = Collections.singletonList(cancerSummary);
        String[] jsonArray = {"DTXSID9020112"};
        String jsonBody = new ObjectMapper().writeValueAsString(jsonArray);
        		
        when(cancerSummaryRepository.findByDtxsidInOrderByDtxsidAsc(jsonArray, CancerSummary.class)).thenReturn(summary);
        
        
        mockMvc.perform(post("/hazard/cancer-summary/search/by-dtxsid/")
        		.accept(MediaType.APPLICATION_JSON)
        		.contentType(MediaType.APPLICATION_JSON)
        		.content(jsonBody))
        		.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].dtxsid").value(cancerSummary.getDtxsid()))
                .andReturn();
	}

}

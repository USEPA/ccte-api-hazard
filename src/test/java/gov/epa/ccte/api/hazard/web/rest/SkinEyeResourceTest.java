package gov.epa.ccte.api.hazard.web.rest;

import org.junit.jupiter.api.BeforeEach;

//This will test REST end-points in the SkinEyeResource.java using WebMvcTest and MockitoBean

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

import gov.epa.ccte.api.hazard.domain.SkinEye;
import gov.epa.ccte.api.hazard.repository.SkinEyeRepository;

import java.util.*;

@ActiveProfiles("test")
@WebMvcTest(SkinEyeResource.class)
@RunWith(MockitoJUnitRunner.class)
class SkinEyeResourceTest {

    @Autowired
    private MockMvc mockMvc;
    
    @MockitoBean
    private SkinEyeRepository skinEyeRepository;
    
    private SkinEye skinEye;
    
    @BeforeEach
    void setUp() {
    	skinEye = SkinEye.builder()
    			.id(2334)
    			.authority("Authoritative")
    			.classification("Eye Dam. 1")
    			.dtxsid("DTXSID7020182")
    			.endpoint("Eye Irritation")
    			.glp(null)
    			.guideline(null)
    			.recordUrl("https://echa.europa.eu/information-on-chemicals/annex-vi-to-clp")
    			.reliability("-")
    			.resultText("Causes serious eye damage H318 Score of VH was assigned based on a hazard code of H318")
    			.score("VH")
    			.skinEyeHash("-")
    			.skinEyeId(325577)
    			.skinEyeUuid(null)
    			.source("ECHA CLP")
    			.species("-")
    			.strain("-")
    			.studyType("-")
    			.year(-1)
    			.build();
    }
    
    

    @Test
    void testGetCancerSummaryByDtxsid() throws Exception {
        final List<SkinEye> skinEyeData = Collections.singletonList(skinEye);

        when(skinEyeRepository.findAllByDtxsid("DTXSID7020182", SkinEye.class)).thenReturn(skinEyeData);

        mockMvc.perform(get("/hazard/skin-eye/search/by-dtxsid/{dtxsid}", "DTXSID7020182"))
				.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].dtxsid").value(skinEye.getDtxsid()));
	}
    

    @Test
    void testCancerSummaryByBatchDtxsid() throws Exception {
    	final List<SkinEye> skinEyeData = Collections.singletonList(skinEye);
        String[] jsonArray = {"DTXSID7020182"};
        String jsonBody = new ObjectMapper().writeValueAsString(jsonArray);
        		
        when(skinEyeRepository.findByDtxsidInOrderByDtxsidAsc(jsonArray, SkinEye.class)).thenReturn(skinEyeData);
        
        
        mockMvc.perform(post("/hazard/skin-eye/search/by-dtxsid/")
        		.accept(MediaType.APPLICATION_JSON)
        		.contentType(MediaType.APPLICATION_JSON)
        		.content(jsonBody))
        		.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].dtxsid").value(skinEye.getDtxsid()))
                .andReturn();
	}

}


package gov.epa.ccte.api.hazard.web.rest;

import org.junit.jupiter.api.BeforeEach;

//This will test REST end-points in the HawcResource.java using WebMvcTest and MockitoBean

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.when;

import gov.epa.ccte.api.hazard.domain.Hawc;
import gov.epa.ccte.api.hazard.repository.HawcRepository;

import java.util.*;

@ActiveProfiles("test")
@WebMvcTest(HawcResource.class)
@RunWith(MockitoJUnitRunner.class)
class HawcResourceTest {
	
    @Autowired
    private MockMvc mockMvc;
    
    @MockitoBean
    private HawcRepository hawcRepository;
    
    private Hawc hawc;

    @BeforeEach
    void setUp() {
    	
    	hawc = Hawc.builder()
    			.id(100500243)
    			.name("ORD IRIS Assessment Inorganic Arsenic")
    			.dtxsid("DTXSID4023886")
    			.year(2025)
    			.ccdUrl("https://comptox.epa.gov/dashboard/dsstoxdb/results?search=DTXSID4023886")
    			.hawcUrl("https://hawc.epa.gov/assessment/100500243/")
    			.build();
    	
    }
    
    @Test
    void testGetHawcByDtxsid() throws Exception {
        final List<Object> data = Collections.singletonList(hawc);

        when(hawcRepository.findByDtxsid("DTXSID4023886")).thenReturn(data);

        mockMvc.perform(get("/hazard/hawc/search/by-dtxsid/{dtxsid}", "DTXSID4023886"))
				.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].dtxsid").value(hawc.getDtxsid()));
    
    }
}

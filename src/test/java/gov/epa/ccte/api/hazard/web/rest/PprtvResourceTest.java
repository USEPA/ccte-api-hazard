package gov.epa.ccte.api.hazard.web.rest;

import org.junit.jupiter.api.BeforeEach;

//This will test REST end-points in the PprtvResource.java using WebMvcTest and MockitoBean

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

import gov.epa.ccte.api.hazard.domain.Pprtv;
import gov.epa.ccte.api.hazard.repository.PprtvRepository;

import java.util.*;

@ActiveProfiles("test")
@WebMvcTest(PprtvResource.class)
@RunWith(MockitoJUnitRunner.class)
class PprtvResourceTest {
	
    @Autowired
    private MockMvc mockMvc;
    
    @MockitoBean
    private PprtvRepository pprtvRepository;
    
    private Pprtv pprtv;

    @BeforeEach
    void setUp() {
    	
    	pprtv = Pprtv.builder()
    			.id(18)
    			.dtxsid("DTXSID0023872")
    			.name("Ammonia")
    			.casrn("7664-41-7")
    			.lastRevision(2005)
    			.pprtvAssessment("https://cfpub.epa.gov/ncea/pprtv/documents/Ammonia.pdf")
    			.irisLink("https://cfpub.epa.gov/ncea/iris2/chemicalLanding.cfm?substance_nmbr=422")
    			.rfcValue("See IRIS")
    			.rfdValue("Not available")
    			.woe("Not available")
    			.build();
    	
    }
    
    @Test
    void testGetPprtvDataByDtxsid() throws Exception {
        final List<Object> data = Collections.singletonList(pprtv);

        when(pprtvRepository.findAllByDtxsid("DTXSID0023872")).thenReturn(data);

        mockMvc.perform(get("/hazard/pprtv/search/by-dtxsid/{dtxsid}", "DTXSID0023872"))
				.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].dtxsid").value(pprtv.getDtxsid()));
    
    }
}
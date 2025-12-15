package gov.epa.ccte.api.hazard.web.rest;

import org.junit.jupiter.api.BeforeEach;

//This will test REST end-points in the IrisResource.java using WebMvcTest and MockitoBean

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

import gov.epa.ccte.api.hazard.domain.Iris;
import gov.epa.ccte.api.hazard.repository.IrisRepository;

import java.time.LocalDate;
import java.util.*;

@ActiveProfiles("test")
@WebMvcTest(IrisResource.class)
@RunWith(MockitoJUnitRunner.class)
class IrisResourceTest {
	
    @Autowired
    private MockMvc mockMvc;
    
    @MockitoBean
    private IrisRepository irisRepository;
    
    private Iris iris;

    @BeforeEach
    void setUp() {
    	
    	iris = Iris.builder()
    			.dtxsid("DTXSID7020182")
    			.chemicalName("Bisphenol A")
    			.casrn("80-05-7")
    			.lastSignificantRevision(LocalDate.of(1988,9,26))
    			.literatureScreeningReview("Yes")
    			.criticalEffectsSystems(null)
    			.rfdChronic(null)
    			.rfdSubchronic(null)
    			.rfcChronic(null)
    			.rfcSubchronic(null)
    			.tumorSite(null)
    			.irisUrl("https://iris.epa.gov/ChemicalLanding/&substance_nmbr=356")
    			.build();
    	
    }
    
    @Test
    void testGetIrisDataByDtxsid() throws Exception {
        final List<Object> data = Collections.singletonList(iris);

        when(irisRepository.findByDtxsid("DTXSID7020182")).thenReturn(data);

        mockMvc.perform(get("/hazard/iris/search/by-dtxsid/{dtxsid}", "DTXSID7020182"))
				.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].dtxsid").value(iris.getDtxsid()));
    
    }
}

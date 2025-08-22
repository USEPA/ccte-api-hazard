package gov.epa.ccte.api.hazard.web.rest;

import org.junit.jupiter.api.BeforeEach;

//This will test REST end-points in the ToxRefSummaryResource.java using WebMvcTest and MockitoBean

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

import gov.epa.ccte.api.hazard.domain.ToxRefSummary;
import gov.epa.ccte.api.hazard.repository.ToxRefSummaryRepository;

import java.time.LocalDate;
import java.util.*;

@ActiveProfiles("test")
@WebMvcTest(ToxRefSummaryResource.class)
@RunWith(MockitoJUnitRunner.class)
class ToxRefSummaryResourceTest {

    @Autowired
    private MockMvc mockMvc;
    
    @MockitoBean
    private ToxRefSummaryRepository toxRefSummaryRepository;
    
    private ToxRefSummary toxRefSummary;
    
    @BeforeEach
    void setUp() {
    	toxRefSummary = ToxRefSummary.builder()
    			.id(1482L)
    			.studyId(2108)
    			.dtxsid("DTXSID9020112")
    			.casrn("1912-24-9")
    			.name("Atrazine")
    			.studySource("opp_der")
    			.studySourceId("41392401")
    			.citation("Thompson, S.; Batastini, G.; Arthur, A. (1990) 13/52-Week Oral To- xicity Study in Dogs: Lab Project Number: 872151.  Unpublished study prepared by Ciba-Geigy Corp.  784 p.")
    			.studyYear(1990)
    			.studyType("CHR")
    			.studyTypeGuideline("Chronic toxicity")
    			.species("dog")
    			.strainGroup("beagle")
    			.strain("beagle")
    			.adminRoute("Oral")
    			.adminMethod("Feed")
    			.doseStart(0)
    			.doseStartUnit("week")
    			.doseEnd(52)
    			.doseEndUnit("week")
    			.exportDate(LocalDate.of(2025,01,17))
    			.version("v2_1")
    			.build();
    }
    

    @Test
    void testGetToxRefSummaryByStudyId() throws Exception {
        final List<ToxRefSummary> summary = Collections.singletonList(toxRefSummary);

        when(toxRefSummaryRepository.findAllByStudyId(2108, ToxRefSummary.class)).thenReturn(summary);

        mockMvc.perform(get("/hazard/toxref/summary/search/by-study-id/{studyId}", "2108"))
				.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].studyId").value(toxRefSummary.getStudyId()));
	}
    
    @Test
    void testGetToxRefSummaryByDtxsid() throws Exception {
        final List<ToxRefSummary> summary = Collections.singletonList(toxRefSummary);

        when(toxRefSummaryRepository.findAllByDtxsid("DTXSID9020112", ToxRefSummary.class)).thenReturn(summary);

        mockMvc.perform(get("/hazard/toxref/summary/search/by-dtxsid/{dtxsid}", "DTXSID9020112"))
				.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].dtxsid").value(toxRefSummary.getDtxsid()));
	}
    
    @Test
    void testGetToxRefSummaryByStudyType() throws Exception {
        final List<ToxRefSummary> summary = Collections.singletonList(toxRefSummary);

        when(toxRefSummaryRepository.findAllByStudyType("CHR", ToxRefSummary.class)).thenReturn(summary);

        mockMvc.perform(get("/hazard/toxref/summary/search/by-study-type/{studyType}", "CHR"))
				.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].studyType").value(toxRefSummary.getStudyType()));
	}
    
}
    

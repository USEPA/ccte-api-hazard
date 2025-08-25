package gov.epa.ccte.api.hazard.web.rest;

import org.junit.jupiter.api.BeforeEach;

//This will test REST end-points in the ToxRefEffectsResource.java using WebMvcTest and MockitoBean

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.when;

import gov.epa.ccte.api.hazard.domain.ToxRefEffects;
import gov.epa.ccte.api.hazard.repository.ToxRefEffectsRepository;
import gov.epa.ccte.api.hazard.service.ToxRefService;
import gov.epa.ccte.api.hazard.web.rest.requests.ToxRefPage;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@ActiveProfiles("test")
@WebMvcTest(ToxRefEffectsResource.class)
@RunWith(MockitoJUnitRunner.class)
class ToxRefEffectsResourceTest {

    @Autowired
    private MockMvc mockMvc;
    
    @MockitoBean
    private ToxRefEffectsRepository toxRefEffectsRepository;
    @MockitoBean 
    private ToxRefService toxRefService;
    
    private ToxRefEffects toxRefEffects;
    private ToxRefPage studyType;
    
    @BeforeEach
    void setUp() {
    	toxRefEffects = ToxRefEffects.builder()
    			.id(131478)
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
    			.doseDuration(52)
    			.doseDurationUnit("week")
    			.doseStart(0)
    			.doseStartUnit("week")
    			.doseEnd(52)
    			.doseEndUnit("week")
    			.dosePeriod("terminal")
    			.doseLevel(2)
    			.conc((double) 100)
    			.concUnit("ppm")
    			.vehicle(null)
    			.doseComment(null)
    			.doseAdjusted(BigDecimal.valueOf(3.43))
    			.doseAdjustedUnit("mg/kg/day")
    			.sex("F")
    			.generation("F0")
    			.lifeStage("adult")
    			.numAnimals(BigDecimal.valueOf(4))
    			.tgComment(null)
    			.endpointCategory("systemic")
    			.endpointType("pathology microscopic")
    			.endpointTarget("liver")
    			.effectDesc("hemosiderosis")
    			.effectDescFree("NA")
    			.cancerRelated(false)
    			.targetSite("NA")
    			.direction(1)
    			.effectComment(null)
    			.treatmentRelated(false)
    			.criticalEffect(false)
    			.sampleSize("4")
    			.effectVal((double) 0)
    			.effectValUnit("incidence")
    			.effectVar(null)
    			.effectVarType(null)
    			.time((double) 52)
    			.timeUnit("week")
    			.noQuantDataReported(false)
    			.exportDate(LocalDate.of(2024,12,02))
    			.version("v2_1")
    			.build();
    
    	studyType = ToxRefPage.builder()
    			.studyType("CHR")
    			.data(Collections.singletonList(toxRefEffects))
    			.recordsOnPage(10000)
    			.totalRecords(127573L)
    			.pageNumber(1)
    			.totalPages(13)
    			.build();
    }
    
    @Test
    void testGetToxRefEffectsByStudyId() throws Exception {
        final List<ToxRefEffects> data = Collections.singletonList(toxRefEffects);

        when(toxRefEffectsRepository.findAllByStudyId(2108, ToxRefEffects.class)).thenReturn(data);

        mockMvc.perform(get("/hazard/toxref/effects/search/by-study-id/{studyId}", "2108"))
				.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].studyId").value(toxRefEffects.getStudyId()));
	}
    
    @Test
    void testGetToxRefEffectsByDtxsid() throws Exception {
        final List<ToxRefEffects> data = Collections.singletonList(toxRefEffects);

        when(toxRefEffectsRepository.findAllByDtxsid("DTXSID9020112", ToxRefEffects.class)).thenReturn(data);

        mockMvc.perform(get("/hazard/toxref/effects/search/by-dtxsid/{dtxsid}", "DTXSID9020112"))
				.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].dtxsid").value(toxRefEffects.getDtxsid()));
	}
    
    @Test
    void testGetToxRefEffectsByStudyType() throws Exception {
        Integer pageNumber = 1;
        Integer pageSize = 10000;
        Pageable pageable = PageRequest.of(pageNumber-1, pageSize);

        when(toxRefService.getAllToxRefEffectsByStudyType("CHR", pageSize, pageNumber, pageable)).thenReturn(studyType);

        mockMvc.perform(get("/hazard/toxref/effects/search/by-study-type/{studyType}", "CHR"))
				.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].studyType").value(studyType.getStudyType()));
	}
    
}

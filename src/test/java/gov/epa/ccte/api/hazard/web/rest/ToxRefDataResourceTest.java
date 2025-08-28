package gov.epa.ccte.api.hazard.web.rest;

import org.junit.jupiter.api.BeforeEach;

//This will test REST end-points in the ToxRefDataResource.java using WebMvcTest and MockitoBean

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

import gov.epa.ccte.api.hazard.domain.ToxRefData;
import gov.epa.ccte.api.hazard.repository.ToxRefDataRepository;
import gov.epa.ccte.api.hazard.service.ToxRefService;
import gov.epa.ccte.api.hazard.web.rest.requests.ToxRefPage;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@ActiveProfiles("test")
@WebMvcTest(ToxRefDataResource.class)
@RunWith(MockitoJUnitRunner.class)
class ToxRefDataResourceTest {

    @Autowired
    private MockMvc mockMvc;
    
    @MockitoBean
    private ToxRefDataRepository toxRefDataRepository;
    @MockitoBean 
    private ToxRefService toxRefService;
    
    private ToxRefData toxRefData;
    private ToxRefPage studyType;
    
    @BeforeEach
    void setUp() {
    	toxRefData = ToxRefData.builder()
    			.id(137731)
    			.studyId(2126)
    			.dtxsid("DTXSID9020112")
    			.casrn("1912-24-9")
    			.name("Atrazine")
    			.studySource("opp_der")
    			.studySourceId("158930")
    			.citation("Wingard, B. (1986) Twenty Four Month Combined Chronic Oral Toxicity and Oncogenicity Study in Rats Utilizing Atrazine Technical: Study 410-1102.  Unpublished study prepared by American Bio- genics Corp.  5838 p.")
    			.studyYear(1986)
    			.studyType("CHR")
    			.studyTypeGuideline("Combined chronic toxicity/carcinogenicity")
    			.species("rat")
    			.strainGroup("sprague dawley")
    			.strain("Sprague Dawley")
    			.adminRoute("Oral")
    			.adminMethod("Feed")
    			.doseDuration(13)
    			.doseDurationUnit("month")
    			.doseStart(0)
    			.doseStartUnit("day")
    			.doseEnd(2)
    			.doseEndUnit("year")
    			.dosePeriod("interim2")
    			.doseLevel(4)
    			.conc((double) 1000)
    			.concUnit("ppm")
    			.vehicle(null)
    			.doseComment(null)
    			.doseAdjusted(BigDecimal.valueOf(50))
    			.doseAdjustedUnit("mg/kg/day")
    			.sex("F")
    			.generation("F0")
    			.lifeStage(null)
    			.numAnimals(BigDecimal.valueOf(10))
    			.tgComment(null)
    			.endpointCategory(null)
    			.endpointType(null)
    			.endpointTarget(null)
    			.effectDesc(null)
    			.effectDescFree(null)
    			.cancerRelated(null)
    			.targetSite(null)
    			.direction(null)
    			.effectComment(null)
    			.treatmentRelated(null)
    			.criticalEffect(null)
    			.sampleSize(null)
    			.effectVal(null)
    			.effectValUnit(null)
    			.effectVar(null)
    			.effectVarType(null)
    			.time(null)
    			.timeUnit(null)
    			.noQuantDataReported(null)
    			.exportDate(LocalDate.of(2024,12,02))
    			.version("v2_1")
    			.build();
    
    	studyType = ToxRefPage.builder()
    			.studyType("CHR")
    			.data(Collections.singletonList(toxRefData))
    			.recordsOnPage(10000)
    			.totalRecords(36565L)
    			.pageNumber(1)
    			.totalPages(4)
    			.build();
    }
    
    @Test
    void testGetToxRefDataByStudyId() throws Exception {
        final List<ToxRefData> data = Collections.singletonList(toxRefData);

        when(toxRefDataRepository.findAllByStudyId(2126, ToxRefData.class)).thenReturn(data);

        mockMvc.perform(get("/hazard/toxref/data/search/by-study-id/{studyId}", "2126"))
				.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].studyId").value(toxRefData.getStudyId()));
	}
    
    @Test
    void testGetToxRefDataByDtxsid() throws Exception {
        final List<ToxRefData> data = Collections.singletonList(toxRefData);

        when(toxRefDataRepository.findAllByDtxsid("DTXSID9020112", ToxRefData.class)).thenReturn(data);

        mockMvc.perform(get("/hazard/toxref/data/search/by-dtxsid/{dtxsid}", "DTXSID9020112"))
				.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].dtxsid").value(toxRefData.getDtxsid()));
	}
    
    @Test
    void testGetToxRefDataByStudyType() throws Exception {
        Integer pageNumber = 1;
        Integer pageSize = 10000;
        Pageable pageable = PageRequest.of(pageNumber-1, pageSize);

        when(toxRefService.getAllToxRefDataByStudyType("CHR", pageSize, pageNumber, pageable)).thenReturn(studyType);

        mockMvc.perform(get("/hazard/toxref/data/search/by-study-type/{studyType}", "CHR"))
				.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].studyType").value(studyType.getStudyType()));
	}
    
}
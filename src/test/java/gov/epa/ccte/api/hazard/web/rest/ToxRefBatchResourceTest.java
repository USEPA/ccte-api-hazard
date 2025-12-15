package gov.epa.ccte.api.hazard.web.rest;

import org.junit.jupiter.api.BeforeEach;

//This will test REST end-points in the ToxRefBatchResource.java using WebMvcTest and MockitoBean

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

import gov.epa.ccte.api.hazard.domain.ToxRefBatch;
import gov.epa.ccte.api.hazard.repository.ToxRefBatchRepository;

import java.util.*;

@ActiveProfiles("test")
@WebMvcTest(ToxRefBatchResource.class)
@RunWith(MockitoJUnitRunner.class)
class ToxRefBatchResourceTest {

    @Autowired
    private MockMvc mockMvc;
    
    @MockitoBean
    private ToxRefBatchRepository toxRefBatchRepository;
    
    private ToxRefBatch toxRefBatch;
    
    @BeforeEach
    void setUp() {
    	toxRefBatch = ToxRefBatch.builder()
    			.studyId(2126)
    			.dtxsid("DTXSID9020112")
    			.casrn("1912-24-9")
    			.name("Atrazine")
    			.studySource("opp_der")
    			.studySourceId("158930")
    			.citation("Wingard, B. (1986) Twenty Four Month Combined Chronic Oral Toxicity and Oncogenicity Study in Rats Utilizing Atrazine Technical: Study 410-1102.  Unpublished study prepared by American Bio- genics Corp.  5838 p.")
    			.studyYear("1986")
    			.studyType("CHR")
    			.studyTypeGuideline("Combined chronic toxicity/carcinogenicity")
    			.species("rat")
    			.strainGroup("sprague dawley")
    			.strain("Sprague Dawley")
    			.adminRoute("Oral")
    			.adminMethod("Feed")
    			.doseDuration("24")
    			.doseDurationUnit("month")
    			.doseStart(0)
    			.doseStartUnit("day")
    			.doseEnd(2)
    			.doseEndUnit("year")
    			.dosePeriod("terminal")
    			.doseLevel(3)
    			.conc("500")
    			.concUnit("ppm")
    			.vehicle(null)
    			.doseComment(null)
    			.doseAdjusted("25.0")
    			.doseAdjustedUnit("mg/kg/day")
    			.sex("M")
    			.generation("F0")
    			.lifeStage("adult")
    			.numAnimals("90.0")
    			.tgComment(null)
    			.endpointCategory("systemic")
    			.endpointType("in life observation")
    			.endpointTarget("body weight")
    			.effectDesc("body weight gain")
    			.effectDescFree("body weight")
    			.cancerRelated(false)
    			.targetSite("NA")
    			.direction(-1)
    			.effectComment(null)
    			.treatmentRelated("true")
    			.criticalEffect(true)
    			.sampleSize("90")
    			.effectVal("678")
    			.effectValUnit("g")
    			.effectVar("95.4")
    			.effectVarType("SD")
    			.time("95.4")
    			.timeUnit("week")
    			.noQuantDataReported(false)
    			.tbsKey(1852L)
    			.build();
    }
    
    @Test
    void testtoxRefBatch() throws Exception {
        final List<ToxRefBatch> batch = Collections.singletonList(toxRefBatch);
        String[] jsonArray = {"DTXSID9020112"};
        String jsonBody = new ObjectMapper().writeValueAsString(jsonArray);
        
        when(toxRefBatchRepository.findByDtxsidInOrderByDtxsidAsc(jsonArray, ToxRefBatch.class)).thenReturn(batch);

        mockMvc.perform(post("/hazard/toxref/search/by-dtxsid/")
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON)
			.content(jsonBody))
			.andDo(MockMvcResultHandlers.print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$[0].dtxsid").value(toxRefBatch.getDtxsid()))
			.andReturn();
	}
}

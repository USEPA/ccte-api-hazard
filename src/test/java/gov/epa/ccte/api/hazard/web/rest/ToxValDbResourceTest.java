package gov.epa.ccte.api.hazard.web.rest;

import org.junit.jupiter.api.BeforeEach;

//This will test REST end-points in the ToxValDbResource.java using WebMvcTest and MockitoBean

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

import gov.epa.ccte.api.hazard.domain.ToxValDb;
import gov.epa.ccte.api.hazard.repository.ToxValDbRepository;

import java.util.*;

@ActiveProfiles("test")
@WebMvcTest(ToxValDbResource.class)
@RunWith(MockitoJUnitRunner.class)
class ToxValDbResourceTest {

    @Autowired
    private MockMvc mockMvc;
    
    
    @MockitoBean
    private ToxValDbRepository toxValDbRepository;
    private ToxValDb toxValDb;
    
    @BeforeEach
    void setUp() {
    	toxValDb = ToxValDb.builder()
    			.id(32637)
    			.dtxsid("DTXSID7020182")
    			.casrn("80-05-7")
    			.name("Bisphenol A")
    			.source("ECHA IUCLID")
    			.subsource("Toxicity Reproduction")
    			.toxvalType("NOAEL")
    			.toxvalTypeDefinition("The dose lower than the LOAEL; NOAEL is the highest dose tested which did not produce the critical effect in the test species.")
    			.toxvalSubtype("-")
    			.toxvalTypeSuperCategory("Dose Response Summary Value")
    			.qualifier("=")
    			.toxvalNumeric((double) 50)
    			.toxvalUnits("mg/kg-day")
    			.riskAssessmentClass("-")
    			.humanEco("human health")
    			.studyType("reproduction developmental")
    			.studyDurationClass("-")
    			.studyDurationValue((double) -999)
    			.studyDurationUnits("-")
    			.speciesCommon("Rat")
    			.strain("Wistar")
    			.latinName("Rattus norvegicus")
    			.speciesSupercategory("Mammals")
    			.sex("M/F")
    			.generation("P0")
    			.lifestage("-")
    			.exposureRoute("oral")
    			.exposureMethod("gavage")
    			.exposureForm("-")
    			.media("-")
    			.toxicologicalEffect("P0: mortality|P0: clinical biochemistry|P0: organ weights and organ / body weight ratios|P0: histopathology: non-neoplastic")
    			.experimentalRecord("experimental")
    			.studyGroup("ECHA IUCLID:14728247:M/F:P0-")
    			.longRef("-")
    			.doi("-")
    			.title("-")
    			.author("-")
    			.year("2020")
    			.guideline("-")
    			.quality("1 (reliable without restriction)")
    			.qcCategory("Programmatically extracted from structured data source; Source overall passed QC, but this record was not manually checked")
    			.sourceHash("ToxValhc_10a19969dded60327d5a3bd4af491b30")
    			.externalSourceId("-")
    			.externalSourceIdDesc("-")
    			.sourceUrl("https://echa.europa.eu/")
    			.subsourceUrl("https://echa.europa.eu/en/registration-dossier/-/registered-dossier/15752?documentUUID=da4fb77b-5b73-45c1-b64b-4bf313fcd139")
    			.storedSourceRecord("https://clowder.edap-cluster.com/files/669eab68e4b0a7c65d1ba1a2")
    			.toxvalTypeOriginal("NOAEL")
    			.toxvalSubtypeOriginal("-")
    			.toxvalNumericOriginal((double) 50)
    			.toxvalUnitsOriginal("mg/kg bw/day (actual dose received)")
    			.studyTypeOriginal("extended one-generation reproductive toxicity - with developmental neurotoxicity (Cohorts 1A, 1B without extension, 2A and 2B)")
    			.studyDurationClassOriginal("-")
    			.studyDurationValueOriginal("-")
    			.studyDurationUnitsOriginal("-")
    		    .speciesOriginal("rat")
    		    .strainOriginal("Wistar")
    		    .sexOriginal("male/female")
    		    .generationOriginal("P0")
    		    .lifestageOriginal("-")
    		    .exposureRouteOriginal("oral")
    		    .exposureMethodOriginal("gavage")
    		    .exposureFormOriginal("-")
    		    .mediaOriginal("-")
    		    .toxicologicalEffectOriginal("P0: mortality|P0: clinical biochemistry|P0: organ weights and organ / body weight ratios|P0: histopathology: non-neoplastic")
    			.originalYear("2020")
    			.build();
    }
    
    

    @Test
    void testGetToxValDbDataByDtxsid() throws Exception {
        final List<ToxValDb> hazardData = Collections.singletonList(toxValDb);

        when(toxValDbRepository.findAllByDtxsid("DTXSID7020182", ToxValDb.class)).thenReturn(hazardData);

        mockMvc.perform(get("/hazard/toxval/search/by-dtxsid/{dtxsid}", "DTXSID7020182"))
				.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].dtxsid").value(toxValDb.getDtxsid()));
	}
    

    @Test
    void testToxValDbDataByBatchDtxsid() throws Exception {
    	final List<ToxValDb> hazardData = Collections.singletonList(toxValDb);
        String[] jsonArray = {"DTXSID7020182"};
        String jsonBody = new ObjectMapper().writeValueAsString(jsonArray);
        		
        when(toxValDbRepository.findByDtxsidInOrderByDtxsidAsc(jsonArray, ToxValDb.class)).thenReturn(hazardData);
        
        
        mockMvc.perform(post("/hazard/toxval/search/by-dtxsid/")
        		.accept(MediaType.APPLICATION_JSON)
        		.contentType(MediaType.APPLICATION_JSON)
        		.content(jsonBody))
        		.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].dtxsid").value(toxValDb.getDtxsid()))
                .andReturn();
	}

}
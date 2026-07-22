package gov.epa.ccte.api.hazard.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import gov.epa.ccte.api.hazard.domain.ToxRefData;
import gov.epa.ccte.api.hazard.domain.ToxRefEffects;
import gov.epa.ccte.api.hazard.domain.ToxRefObs;
import gov.epa.ccte.api.hazard.domain.ToxRefSummary;

@Sql(scripts = {"/schema.sql", "/data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@DataJpaTest(properties = "spring.sql.init.mode=never")
@ActiveProfiles("test")
public class ToxRefRepositoriesTests {
   
    @Autowired
    private	ToxRefDataRepository data;
    @Autowired
    private ToxRefEffectsRepository effects;
    @Autowired
    private	ToxRefObsRepository observations;
    @Autowired
    private ToxRefSummaryRepository summary;
    
    
    @Test
    void testDataLoaded(){
        assertThat(data.findAll().size()).isEqualTo(10);
        assertThat(effects.findAll().size()).isEqualTo(10);
        assertThat(observations.findAll().size()).isEqualTo(10);
        assertThat(summary.findAll().size()).isEqualTo(6);
        
    }
    
    
    // ToxrefDataRepository
    @Test
    void testToxRefDataByStudyId(){
       assertThat(data.findAllByStudyId(2612, ToxRefData.class)).size().isEqualTo(5);
       
       assertThat(data.findAllByStudyId(2108, ToxRefData.class)).size().isEqualTo(4);
       
	}
    
    @Test
    void testToxRefDataByDtxsid(){
       assertThat(data.findAllByDtxsid("DTXSID7020182", ToxRefData.class)).size().isEqualTo(5);
       
       assertThat(data.findAllByDtxsid("DTXSID9020112", ToxRefData.class)).size().isEqualTo(5);
       
	}
    
    @Test
    void testToxRefDataByStudyType(){
       Integer pageSize = 10;
       Integer pageNumber = 1;
       Pageable pageable = PageRequest.of(pageNumber-1, pageSize);
       assertThat(data.findAllByStudyTypeOrderByStudyIdAsc("DEV", pageable)).size().isEqualTo(5);
       
	}
    
    // ToxrefEffectsRepository
    @Test
    void testToxRefEffectsByStudyId(){
       assertThat(effects.findAllByStudyId(2612, ToxRefEffects.class)).size().isEqualTo(5);
       
       assertThat(effects.findAllByStudyId(2108, ToxRefEffects.class)).size().isEqualTo(5);
       
	}
    
    @Test
    void testToxRefEffectsByDtxsid(){
       assertThat(effects.findAllByDtxsid("DTXSID7020182", ToxRefEffects.class)).size().isEqualTo(5);
       
       assertThat(effects.findAllByDtxsid("DTXSID9020112", ToxRefEffects.class)).size().isEqualTo(5);
       
	}
    
    @Test
    void testToxRefEffectsByStudyType(){
       Integer pageSize = 10;
       Integer pageNumber = 1;
       Pageable pageable = PageRequest.of(pageNumber-1, pageSize);
       assertThat(effects.findAllByStudyTypeOrderByStudyIdAsc("CHR", pageable)).size().isEqualTo(5);
       
	}
    
    // ToxrefObsRepository
    @Test
    void testToxRefObservationsByStudyId(){
       assertThat(observations.findAllByStudyId(2612, ToxRefObs.class)).size().isEqualTo(5);
       
       assertThat(observations.findAllByStudyId(2123, ToxRefObs.class)).size().isEqualTo(3);
       
	}
    
    @Test
    void testToxRefObservationsByDtxsid(){
       assertThat(observations.findAllByDtxsid("DTXSID7020182", ToxRefObs.class)).size().isEqualTo(5);
       
       assertThat(observations.findAllByDtxsid("DTXSID9020112", ToxRefObs.class)).size().isEqualTo(5);
       
	}
    
    @Test
    void testToxRefObservationsByStudyType(){
       Integer pageSize = 10;
       Integer pageNumber = 1;
       Pageable pageable = PageRequest.of(pageNumber-1, pageSize);
       assertThat(observations.findAllByStudyTypeOrderByStudyIdAsc("DEV", pageable)).size().isEqualTo(5);
       
	}
    
    // ToxrefSummaryRepository
    @Test
    void testToxRefSummariesByStudyId(){
       assertThat(summary.findAllByStudyId(2612, ToxRefSummary.class)).size().isEqualTo(1);
       
       assertThat(summary.findAllByStudyId(2108, ToxRefSummary.class)).size().isEqualTo(1);
       
	}
    
    @Test
    void testToxRefSummariesByDtxsid(){
       assertThat(summary.findAllByDtxsid("DTXSID7020182", ToxRefSummary.class)).size().isEqualTo(1);
       
       assertThat(summary.findAllByDtxsid("DTXSID9020112", ToxRefSummary.class)).size().isEqualTo(5);
       
	}
    
    @Test
    void testToxRefSummariesByStudyType(){
       assertThat(summary.findAllByStudyType("SUB", ToxRefSummary.class)).size().isEqualTo(3);
              
	}

}

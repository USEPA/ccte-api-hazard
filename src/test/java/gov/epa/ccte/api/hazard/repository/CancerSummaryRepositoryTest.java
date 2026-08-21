package gov.epa.ccte.api.hazard.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import gov.epa.ccte.api.hazard.domain.CancerSummary;

@Sql(scripts = {"/schema.sql", "/data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@DataJpaTest(properties = "spring.sql.init.mode=never")
@ActiveProfiles("test")
class CancerSummaryRepositoryTest {
  
    @Autowired private CancerSummaryRepository repository;
    

    // Now test data loaded or not
    @Test
    void testDataLoaded() {
        assertThat(repository.findAll().size()).isEqualTo(2);
        
    }
    
    @Test
    void testCancerSummaryByDtxsid() { 
    	assertThat(repository.findAllByDtxsid("DTXSID7020182", CancerSummary.class)).size().isEqualTo(0);
        
    	assertThat(repository.findAllByDtxsid("DTXSID9020112", CancerSummary.class)).size().isEqualTo(2);
    }
    
    @Test
    void testCancerSummaryByBatchDtxsid() {
    	String[] dtxsids = {"DTXSID7020182","DTXSID9020112"};
    	assertThat(repository.findByDtxsidInOrderByDtxsidAsc(dtxsids, CancerSummary.class)).size().isEqualTo(2);
        
    }
}

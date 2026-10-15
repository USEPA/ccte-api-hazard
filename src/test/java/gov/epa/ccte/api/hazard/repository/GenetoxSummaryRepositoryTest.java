package gov.epa.ccte.api.hazard.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import gov.epa.ccte.api.hazard.domain.GenetoxSummary;


@Sql(scripts = {"classpath:schema.sql", "classpath:data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@DataJpaTest(properties = "spring.sql.init.mode=never")
@ActiveProfiles("test")
class GenetoxSummaryRepositoryTest {
   
    @Autowired private GenetoxSummaryRepository repository;

    // Now test data loaded or not
    @Test
    void testDataLoaded() {
        assertThat(repository.findAll().size()).isEqualTo(2);
    }
    
    @Test
    void testGenetoxSummariesByDtxsid() { 
    	assertThat(repository.findByDtxsid("DTXSID7020182", GenetoxSummary.class)).size().isEqualTo(1);
        
    	assertThat(repository.findByDtxsid("DTXSID9020112", GenetoxSummary.class)).size().isEqualTo(1);
    }
    
    @Test
    void testGenetoxSummariesByBatchDtxsid() {
    	String[] dtxsids = {"DTXSID7020182","DTXSID9020112"};
    	assertThat(repository.findByDtxsidInOrderByDtxsidAsc(dtxsids, GenetoxSummary.class)).size().isEqualTo(2);
        
    }

}

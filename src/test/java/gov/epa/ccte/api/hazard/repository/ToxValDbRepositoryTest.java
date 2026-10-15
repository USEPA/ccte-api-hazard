package gov.epa.ccte.api.hazard.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import gov.epa.ccte.api.hazard.domain.ToxValDb;

@Sql(scripts = {"/schema.sql", "/data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@DataJpaTest(properties = "spring.sql.init.mode=never")
@ActiveProfiles("test")
class ToxValDbRepositoryTest {
   
    @Autowired private ToxValDbRepository repository;

    // Now test data loaded or not
    @Test
    void testDataLoaded() {
        assertThat(repository.findAll().size()).isEqualTo(10);
    }
    
    @Test
    void testGenetoxDetailsByDtxsid() { 
    	assertThat(repository.findAllByDtxsid("DTXSID7020182", ToxValDb.class)).size().isEqualTo(5);
        
    	assertThat(repository.findAllByDtxsid("DTXSID9020112", ToxValDb.class)).size().isEqualTo(5);
    }
    
    @Test
    void testGenetoxDetailsByBatchDtxsid() {
    	String[] dtxsids = {"DTXSID7020182","DTXSID9020112"};
    	assertThat(repository.findByDtxsidInOrderByDtxsidAsc(dtxsids, ToxValDb.class)).size().isEqualTo(10);
        
    }
}

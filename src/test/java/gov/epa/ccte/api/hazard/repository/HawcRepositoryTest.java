package gov.epa.ccte.api.hazard.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

@Sql(scripts = {"/schema.sql", "/data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@DataJpaTest(properties = "spring.sql.init.mode=never")
@ActiveProfiles("test")
public class HawcRepositoryTest {
    
    @Autowired private HawcRepository repository;

    // Now test data loaded or not
    @Test
    void testDataLoaded() {
        assertThat(repository.findAll().size()).isEqualTo(10);
    }
    
    @Test
    void testHawcByDtxsid() { 
    	assertThat(repository.findByDtxsid("DTXSID8021434")).size().isEqualTo(5);
        
    	assertThat(repository.findByDtxsid("DTXSID3020205")).size().isEqualTo(5);
    }
}

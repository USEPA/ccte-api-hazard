package gov.epa.ccte.api.hazard.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

@Sql(scripts = {"/schema.sql", "/data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@DataJpaTest(properties = "spring.sql.init.mode=never")
@ActiveProfiles("test")
public class PprtvRepositoryTest {
  
    @Autowired private PprtvRepository repository;

    // Now test data loaded or not
    @Test
    void testDataLoaded() {
        assertThat(repository.findAll().size()).isEqualTo(2);
    }
    
    @Test
    void testPprtvByDtxsid() { 
    	assertThat(repository.findAllByDtxsid("DTXSID5036761")).size().isEqualTo(1);
        
    	assertThat(repository.findAllByDtxsid("DTXSID0023872")).size().isEqualTo(1);
    }
}

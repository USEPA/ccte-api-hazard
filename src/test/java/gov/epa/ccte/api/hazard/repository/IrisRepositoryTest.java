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
public class IrisRepositoryTest {

    @Autowired private IrisRepository repository;

    // Now test data loaded or not
    @Test
    void testDataLoaded() {
        assertThat(repository.findAll().size()).isEqualTo(2);
    }
    
    @Test
    void testIrisByDtxsid() { 
    	assertThat(repository.findByDtxsid("DTXSID7020182")).size().isEqualTo(1);
        
    	assertThat(repository.findByDtxsid("DTXSID9020112")).size().isEqualTo(1);
    }
}

package gov.epa.ccte.api.hazard.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import gov.epa.ccte.api.hazard.domain.Adme;

import static org.assertj.core.api.Assertions.*;

@Sql(scripts = {"/schema.sql", "/data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@DataJpaTest(properties = "spring.sql.init.mode=never")
@ActiveProfiles("test")
public class AdmeRepositoryTest {

    @Autowired private AdmeRepository repository;


    // Now test data loaded or not
    @Test
    void testDataLoaded() {
        assertThat(repository.findAll().size()).isEqualTo(10);
    }

    @Test
    void testAdmeIviveByDtxsid() {
        assertThat(repository.findByDtxsid("DTXSID7020182", Adme.class)).size().isEqualTo(5);

        assertThat(repository.findByDtxsid("DTXSID9020112", Adme.class)).size().isEqualTo(5);
    }

    @Test
    void testAdmeIviveByDtxsidCcd() {
        assertThat(repository.findByDtxsidWithLabelColumn("DTXSID7020182")).size().isEqualTo(5);

        assertThat(repository.findByDtxsidWithLabelColumn("DTXSID9020112")).size().isEqualTo(5);
    }
}
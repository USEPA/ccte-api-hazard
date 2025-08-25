package gov.epa.ccte.api.hazard.datatest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import gov.epa.ccte.api.hazard.domain.GenetoxSummary;
import gov.epa.ccte.api.hazard.repository.GenetoxSummaryRepository;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

class GenetoxSummaryRepositoryTest {

    @Autowired
    private DataSource dataSource;
    @Autowired private JdbcTemplate jdbcTemplate;
    @Autowired private TestEntityManager entityManager;
    @Autowired private GenetoxSummaryRepository repository;
    
    @Test
    void injectedComponentsAreNotNull() {
        assertThat(dataSource).isNotNull();
        assertThat(jdbcTemplate).isNotNull();
        assertThat(entityManager).isNotNull();
        assertThat(repository).isNotNull();
    }

    // Now test data loaded or not
    @Test
    void testDataLoaded() {
        assertThat(repository.findAll().size()).isEqualTo(2);
    }

    @Test
    void findByDtxsidInOrderByDtxsidAsc() { assertThat(repository.findByDtxsidInOrderByDtxsidAsc(new String[]{"DTXSID00108550,DTXSID0027775,DTXSID00109168"}, GenetoxSummary.class)).isNotNull(); }

    @Test
    void findByDtxsid() { assertThat(repository.findByDtxsid("DTXSID0020319", GenetoxSummary.class)).isNotNull(); }

}

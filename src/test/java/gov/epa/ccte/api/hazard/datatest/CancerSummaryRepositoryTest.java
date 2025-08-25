package gov.epa.ccte.api.hazard.datatest;

import static org.assertj.core.api.Assertions.assertThat;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import gov.epa.ccte.api.hazard.domain.CancerSummary;
import gov.epa.ccte.api.hazard.repository.CancerSummaryRepository;


@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CancerSummaryRepositoryTest {

    @Autowired
    private DataSource dataSource;
    @Autowired private JdbcTemplate jdbcTemplate;
    @Autowired private TestEntityManager entityManager;
    @Autowired private CancerSummaryRepository repository;
    
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
        assertThat(repository.findAll().size()).isEqualTo(8);
    }
    @Test
    void testFindAllByDtxsid() {
	assertThat(repository.findAllByDtxsid("DTXSID0020319", CancerSummary.class)).isNotNull();	}

    @Test
    void testFindByDtxsidInOrderByDtxsidAsc() {
	assertThat(repository.findByDtxsidInOrderByDtxsidAsc(new String[]{"DTXSID0020319,DTXSID0020076"}, CancerSummary.class)).isNotNull();	}

}

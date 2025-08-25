package gov.epa.ccte.api.hazard.datatest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import gov.epa.ccte.api.hazard.domain.GenetoxDetail;
import gov.epa.ccte.api.hazard.repository.GenetoxDetailRepository;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

class GenetoxDetailRepositoryTest {


    @Autowired
    private DataSource dataSource;
    @Autowired private JdbcTemplate jdbcTemplate;
    @Autowired private TestEntityManager entityManager;
    @Autowired private GenetoxDetailRepository repository;

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
        assertThat(repository.findAll().size()).isEqualTo(24);
    }

    @Test
    void findByDtxsidOrderBySourceAsc() {assertThat(repository.findByDtxsidOrderBySourceAsc("DTXSID00100670", GenetoxDetail.class)).isNotNull();}

    @Test
    void findByDtxsidInOrderByDtxsidAsc() {assertThat(repository.findByDtxsidInOrderByDtxsidAsc(new String[]{"DTXSID00100670,DTXSID00178076"}, GenetoxDetail.class)).isNotNull();}

}

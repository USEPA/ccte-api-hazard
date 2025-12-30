package gov.epa.ccte.api.hazard.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import gov.epa.ccte.api.hazard.domain.GenetoxDetail;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.*;

@Sql(scripts = {"classpath:schema.sql", "classpath:data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Testcontainers
@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class GenetoxDetailRepositoryTest {
    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> pgsqldb = new PostgreSQLContainer<>("postgres:16-alpine");

    @Autowired
    private DataSource dataSource;
    @Autowired private JdbcTemplate jdbcTemplate;
    @Autowired private TestEntityManager entityManager;
    @Autowired private GenetoxDetailRepository repository;

    @Test
    void connectionEstablished(){
        assertThat(pgsqldb.isCreated()).isTrue();
        assertThat(pgsqldb.isRunning()).isTrue();
    }

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
    void testGenetoxDetailsByDtxsid() { 
    	assertThat(repository.findByDtxsidOrderBySourceAsc("DTXSID7020182", GenetoxDetail.class)).size().isEqualTo(3);
        
    	assertThat(repository.findByDtxsidOrderBySourceAsc("DTXSID9020112", GenetoxDetail.class)).size().isEqualTo(5);
    }
    
    @Test
    void testGenetoxDetailsByBatchDtxsid() {
    	String[] dtxsids = {"DTXSID7020182","DTXSID9020112"};
    	assertThat(repository.findByDtxsidInOrderByDtxsidAsc(dtxsids, GenetoxDetail.class)).size().isEqualTo(8);
        
    }
    
    @Test
    void testGenetoxDetailsByDtxsidCcd() { 
    	assertThat(repository.findByDtxsidWithConcatenatedColumn("DTXSID7020182")).size().isEqualTo(3);
        
    	assertThat(repository.findByDtxsidWithConcatenatedColumn("DTXSID9020112")).size().isEqualTo(5);
    }

}

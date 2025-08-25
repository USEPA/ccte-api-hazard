package gov.epa.ccte.api.hazard.web.rest;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for getting the {@link HazardResource}s.
 */
@Slf4j
@RestController
@CrossOrigin
@Hidden // OpenAPI annotation for hiding endpoints from documentation generator
public class HazardResource implements HazardApi {
    private final JdbcTemplate jdbcTemplate;

    public HazardResource(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @SuppressWarnings("rawtypes")
    @Override
    public ResponseEntity health(){

        log.info("checking the health");

        if(jdbcTemplate != null){
            try {
                jdbcTemplate.execute("SELECT 1 ");
                log.debug("DB connection established");

                return ResponseEntity.ok().build();

            } catch (Exception ep){
                return ResponseEntity.notFound().build();
            }
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}

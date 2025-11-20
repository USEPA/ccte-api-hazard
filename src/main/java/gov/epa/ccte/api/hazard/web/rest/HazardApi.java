package gov.epa.ccte.api.hazard.web.rest;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * REST controller for getting the {@link HazardResource}s.
 */
@Tag(name = "Hazard API Health Resource",
        description = "Endpoint for checking health of database connection used by CTX Hazard API")
public interface HazardApi {
    @SuppressWarnings("rawtypes")
    @Hidden
    @GetMapping("/hazard/health")
    ResponseEntity health();
}

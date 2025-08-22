package gov.epa.ccte.api.hazard.web.rest;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * REST controller for getting the {@link HazardResource}s.
 */
@Tag(name = "Hazard Data Resource",
        description = "API endpoint for checking database connection of hazard application.")
public interface HazardApi {
    @SuppressWarnings("rawtypes")
    @Hidden
    @GetMapping("/hazard/health")
    ResponseEntity health();
}

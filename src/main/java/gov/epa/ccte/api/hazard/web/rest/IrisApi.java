package gov.epa.ccte.api.hazard.web.rest;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import gov.epa.ccte.api.hazard.domain.Iris;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * REST controller for getting the {@link gov.epa.ccte.api.hazard.domain.Iris}s.
 */
@Tag(name = "IRIS Resource",
        description = "API endpoints for collecting IRIS data for specified chemical identifier (DTXSID).")
@SecurityRequirement(name = "api_key")
public interface IrisApi {

    /**
     * {@code GET  hazard/iris/search/by-dtxsid/{dtxsid} : get list of iris data for the "dtxsid".
     * @param dtxsid the matching dtxsid of the iris data to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the list of hazard}.
     */
    @Operation(summary = "Get all iris data by dtxsid")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json",
                    schema = @Schema(oneOf = { Iris.class}))),
    })
    @RequestMapping(value = "hazard/iris/search/by-dtxsid/{dtxsid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    List<Iris> irisDataByDtxsid(@Parameter(required = true, description = "DSSTox Substance Identifier", example = "DTXSID7020182") 
    						@PathVariable("dtxsid") String dtxsid);
}

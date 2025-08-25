package gov.epa.ccte.api.hazard.web.rest;

import java.util.List;

import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import gov.epa.ccte.api.hazard.domain.ToxRefBatch;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * REST controller for getting the {@link gov.epa.ccte.api.hazard.domain.ToxRefBatch}s.
 */
@Tag(name = "ToxRefDB Batch Resource",
        description = "API endpoints for collecting ToxRef data by batch of dtxsids.")
@SecurityRequirement(name = "api_key")
public interface ToxRefBatchApi {

    /**
     * {@code POST  hazard/toxref/search/by-dtxsid/{dtxsid} : get list of toxref data for batch "dtxsid".
     * @param dtxsid the matching dtxsid of the toxref data to retrieve.
     * @return the {@link ResponseEntity } with status {@code 200 (OK)} and with body the list of toxref batch data}.
     */
    @Operation(summary = "Get data by batch of dtxsid(s)", description = "Note: Maximum ${application.batch-size} DTXSIDs per request")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json",
                    schema = @Schema(oneOf = {ToxRefBatch.class}))),
            @ApiResponse(responseCode = "400", description = "When user has submitted more then allowed number (${application.batch-size}) of DTXSID(s).",
                    content = @Content(mediaType = "application/json",
                            examples = {@ExampleObject(name = "", value = "{\"title\":\"Validation Error\",\"status\":400,\"detail\":\"System supports only '200' dtxsid at one time, '202' are submitted.\"}", description = "Validation error for more then allowed number of dtxsid(s).")},
                            schema = @Schema(oneOf = {ProblemDetail.class})))
    })
    @PostMapping(value = "/hazard/toxref/search/by-dtxsid/")
    @ResponseBody
    List<ToxRefBatch> toxRefBatch(@io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "JSON array of DSSTox Substance Identifier",
            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = String.class)),
                    examples = {@ExampleObject("\"[\\\"DTXSID5034307\\\",\\\"DTXSID2040363\\\"]\"")})})
                                   @RequestBody String[] dtxsids);
}

package gov.epa.ccte.api.hazard.web.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import gov.epa.ccte.api.hazard.domain.CancerSummary;

import java.util.List;

/**
 * REST controller for getting the {@link gov.epa.ccte.api.hazard.domain.CancerSummary}s.
 */

@Tag(name = "ToxValDb Cancer Summary Resource",
        description = "API endpoints for collecting cancer summary data.")
@SecurityRequirement(name = "api_key")
@RequestMapping( value = "hazard/cancer-summary", produces = MediaType.APPLICATION_JSON_VALUE)
public interface CancerSummaryApi {
    /**
     * {@code GET  hazard/cancer-summary/search/by-dtxsid/{dtxsid} : get list of cancer summary data for the "dtxsid".
     * @param dtxsid the matching dtxsid of the cancer summary data to retrieve.
     * @return the {@link ResponseEntity } with status {@code 200 (OK)} and with body the list of cancer summary}.
     */
    @Operation(summary = "Get data by dtxsid")
    @GetMapping(value = "/search/by-dtxsid/{dtxsid}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json",
                    schema = @Schema(oneOf = {CancerSummary.class})))
    })
    @ResponseBody
    List<CancerSummary> cancerSummaryByDtxsid(@Parameter(required = true, description = "DSSTox Substance Identifier", example = "DTXSID0021125") @PathVariable("dtxsid") String dtxsid);

    /**
     * {@code POST  hazard/cancer-summary/search/by-dtxsid/ : get list of cancer summary data for batch "dtxsid".
     * @param dtxsid the matching dtxsid of the cancer summary data to retrieve.
     * @return the {@link ResponseEntity } with status {@code 200 (OK)} and with body the list of cancer summary}.
     */
    @Operation(summary = "Get data by batch of dtxsid(s)", description = "Note: Maximum ${application.batch-size} DTXSIDs per request")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfull", content = @Content(mediaType = "application/json",
                    schema = @Schema(oneOf = {CancerSummary.class}))),
            @ApiResponse(responseCode = "400", description = "When user has submitted more then allowed number (${application.batch-size}) of DTXSID(s).",
                    content = @Content(mediaType = "application/json",
                            examples = {@ExampleObject(name = "", value = "{\"title\":\"Validation Error\",\"status\":400,\"detail\":\"System supports only '200' dtxsid at one time, '202' are submitted.\"}", description = "Validation error for more then allowed number of dtxsid(s).")},
                            schema = @Schema(oneOf = {ProblemDetail.class})))
    })
    @PostMapping(value = "/search/by-dtxsid/")
    @ResponseBody
    List<CancerSummary> cancerSummaryBatch(@io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "JSON array of DSSTox Substance Identifier",
            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = String.class)),
                    examples = {@ExampleObject("\"[\\\"DTXSID7020182\\\",\\\"DTXSID9020112\\\"]\"")})})
                                              @RequestBody String[] dtxsids);
}

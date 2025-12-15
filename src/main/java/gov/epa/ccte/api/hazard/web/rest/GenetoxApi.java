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

import gov.epa.ccte.api.hazard.domain.GenetoxDetail;
import gov.epa.ccte.api.hazard.domain.GenetoxSummary;
import gov.epa.ccte.api.hazard.projection.CcdGenetoxDetail;

import java.util.List;

/**
 * REST controller for getting the {@link gov.epa.ccte.api.hazard.domain.GenetoxDetail}s. and
 */

@Tag(name = "ToxValDB Genetox Resource",
        description = "Collection of endpoints with genetoxicity data. This curated data is sourced from the US EPA's Toxicity Values Database (ToxValDB).")
@SecurityRequirement(name = "api_key")
@RequestMapping( value = "hazard/genetox", produces = MediaType.APPLICATION_JSON_VALUE)
public interface GenetoxApi {
    /**
     * {@code GET  hazard/genetox/summary/search/by-dtxsid/{dtxsid} : get list of genetox summary data for the "dtxsid".
     * @param dtxsid the matching dtxsid of the genetox summary data to retrieve.
     * @return the {@link ResponseEntity } with status {@code 200 (OK)} and with body the list of genetox summary}.
     */
    @Operation(summary = "Get summary data by DTXSID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json",
                    schema = @Schema(oneOf = {GenetoxSummary.class})))
    })
    @GetMapping(value = "/summary/search/by-dtxsid/{dtxsid}")
    @ResponseBody
    List<GenetoxSummary> genetoxSummaryByDtxsid(@Parameter(required = true, description = "DSSTox Substance Identifier", example = "DTXSID0021125") @PathVariable("dtxsid") String dtxsid);

    /**
     * {@code POST  hazard/genetox/summary/search/by-dtxsid/{dtxsid} : get list of genetox summary data for batch "dtxsid".
     * @param dtxsid the matching dtxsid of the genetox summary data to retrieve.
     * @return the {@link ResponseEntity } with status {@code 200 (OK)} and with body the list of genetox summary}.
     */
    @Operation(summary = "Get summary data for a batch of DTXSID", description = "return genetox summary data for requested DTXSIDs. Note: Maximum ${application.batch-size} DTXSIDs per request")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json",
                    schema = @Schema(oneOf = {GenetoxSummary.class}))),
            @ApiResponse(responseCode = "400", description = "When user has submitted more than allowed number (${application.batch-size}) of DTXSID(s).",
                    content = @Content(mediaType = "application/json",
                            examples = {@ExampleObject(name = "", value = "{\"title\":\"Validation Error\",\"status\":400,\"detail\":\"SSystem supports requests of '200' DTXSIDs at one time, '202' were submitted.\"}", description = "Validation error for more then allowed number of dtxsid(s).")},
                            schema = @Schema(oneOf = {ProblemDetail.class})))
    })
    @PostMapping(value = "/summary/search/by-dtxsid/")
    @ResponseBody
    List<GenetoxSummary> batchSearchSummary(@io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "JSON array of DSSTox Substance Identifier",
            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = String.class)),
                    examples = {@ExampleObject("\"[\\\"DTXSID7020182\\\",\\\"DTXSID9020112\\\"]\"")})})
                                               @RequestBody String[] dtxsids);

    /**
     * {@code GET  hazard/genetox-details/search/by-dtxsid/{dtxsid} : get list of genetox detail data for the "dtxsid".
     * @param dtxsid the matching dtxsid of the genetox detail data to retrieve.
     * @return the {@link ResponseEntity } with status {@code 200 (OK)} and with body the list of genetox detail}.
     */
    @Operation(summary = "Get detailed data by DTXSID with CCD projection",
    				description = "return genetox details for requested DTXSID. There is an available projection aligned with what's available on the CCD Genetoxicity Details page:" +
   				         "ccd-genetox-details. " +
                            "If no projection is specified, the default GenetoxDetails data will be returned.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json",
                    schema = @Schema(oneOf = {GenetoxDetail.class, CcdGenetoxDetail.class})))
    })
    @GetMapping(value = "/details/search/by-dtxsid/{dtxsid}")
    @ResponseBody
    List<?> getGenetoxDetailsByDtxsid(@Parameter(required = true, description = "DSSTox Substance Identifier", example = "DTXSID7020182") 
    									@PathVariable("dtxsid") String dtxsid,
    									@Parameter(description = "Specifies if projection is used. Option: ccd-genetox-details. " +
    											"If no projection is specified, the default GenetoxDetail projection is returned.")
    									@RequestParam(value = "projection", required = false) String projection);

    /**
     * {@code POST  hazard/genetox-details/search/by-dtxsid/{dtxsid} : get list of genetox detail data for batch "dtxsid".
     * @param dtxsid the matching dtxsid of the genetox detail data to retrieve.
     * @return the {@link ResponseEntity } with status {@code 200 (OK)} and with body the list of genetox detail}.
     */
    @Operation(summary = "Get detailed data for a batch of DTXSIDs", description = "return detailed data for requested DTXSIDs. Note: Maximum ${application.batch-size} DTXSIDs per request")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json",
                    schema = @Schema(oneOf = {GenetoxDetail.class}))),
            @ApiResponse(responseCode = "400", description = "User has submitted more than allowed number (${application.batch-size}) of DTXSID(s).",
                    content = @Content(mediaType = "application/json",
                            examples = {@ExampleObject(name = "", value = "{\"title\":\"Validation Error\",\"status\":400,\"detail\":\"System supports requests of '200' DTXSIDs at one time, '202' were submitted.\"}", description = "Validation error for more then allowed number of dtxsid(s).")},
                            schema = @Schema(oneOf = {ProblemDetail.class})))
    })
    @PostMapping(value = "/details/search/by-dtxsid/")
    @ResponseBody
    List<CcdGenetoxDetail> batchSearch(@io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "JSON array of DSSTox Substance Identifier",
            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = String.class)),
                    examples = {@ExampleObject("\"[\\\"DTXSID7020182\\\",\\\"DTXSID9020112\\\"]\"")})})
                                       @RequestBody String[] dtxsids);
}

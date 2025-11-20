package gov.epa.ccte.api.hazard.web.rest;

import gov.epa.ccte.api.hazard.domain.ToxValDb;
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

import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for getting the {@link gov.epa.ccte.api.hazard.domain.ToxValDb}s.
 */
@Tag(name = "ToxValDB Resource",
        description = "Collection of endpoints for human-relevant hazard data curated in the Toxicity Values Database (ToxValDB). ToxValDB is a compilation of information sourced from multiple public datasets, databases and open literature and includes data on thousands of chemicals from tens of thousands of records, with an emphasis on quantitative estimates of relevant points-of-departure from in vivo toxicology studies, such as no- and low- observable adverse effect levels, screening levels, reference doses, tolerable daily intake, etc.")
@SecurityRequirement(name = "api_key")
public interface ToxValDbApi {

    /**
     * {@code GET  hazard/search/by-dtxsid/{dtxsid} : get list of hazard data for the "dtxsid".
     * @param dtxsid the matching dtxsid of the hazard data to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the list of hazard}.
     */
    @Operation(summary = "Get data by DTXSID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json",
                    schema = @Schema(oneOf = { ToxValDb.class})))
    })
    @GetMapping("hazard/toxval/search/by-dtxsid/{dtxsid}")
    @ResponseBody
    List<ToxValDb> getToxValDbDataByDtxsid(@Parameter(required = true, description = "DSSTox Substance Identifier", example = "DTXSID0021125")
                                   @PathVariable("dtxsid") String dtxsid);

    /**
     * {@code POST  hazard/search/by-dtxsid/ : get list of hazard data for the "dtxsid".
     * @param dtxsid the matching dtxsid of the hazard data to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the list of hazard}.
     */
    @Operation(summary = "Get data for a batch of DTXSID(s).", description = "Note: Maximum ${application.batch-size} DTXSIDs per request")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json",
                    schema = @Schema(oneOf = { ToxValDb.class}))),
            @ApiResponse(responseCode = "400", description = "User has submitted more than allowed number (${application.batch-size}) of DTXSID(s).",
                    content = @Content(mediaType = "application/json",
                            examples = {@ExampleObject(value = "{\"title\":\"Validation Error\",\"status\":400,\"detail\":\"System supports requests of '200' DTXSIDs at one time, '202' were submitted.\"}", description = "Validation error for more then allowed number of dtxsid(s).")},
                            schema = @Schema(oneOf = {ProblemDetail.class})))
    })
    @PostMapping("hazard/toxval/search/by-dtxsid/")
    @ResponseBody
    List<ToxValDb> toxValDbDataByBatchDtxsid(@io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "JSON array of DSSTox Substance Identifier",
            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = String.class)),
                    examples = {@ExampleObject("\"[\\\"DTXSID7020182\\\",\\\"DTXSID9020112\\\"]\"")})})
                                @RequestBody String[] dtxsids);

}
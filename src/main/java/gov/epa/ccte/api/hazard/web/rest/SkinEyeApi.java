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
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import gov.epa.ccte.api.hazard.domain.SkinEye;

import java.util.List;

/**
 * REST controller for getting the {@link gov.epa.ccte.api.hazard.domain.SkinEye}s.
 */
@Tag(name = "ToxValDB Skin Eye Resource",
        description = "Collection of endpoints with skin sensitization and eye irritation data. This curated data is sourced from the US EPA's Toxicity Values Database (ToxValDB).")
@SecurityRequirement(name = "api_key")
public interface SkinEyeApi {
    /**
     * {@code GET  hazard/skin-eye/search/by-dtxsid/{dtxsid} : get list of skin eye data for the "dtxsid".
     * @param dtxsid the matching dtxsid of the skin eye data to retrieve.
     * @return the {@link ResponseEntity } with status {@code 200 (OK)} and with body the list of skin eye}.
     */
    @Operation(summary = "Get data by DTXSID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json",
                    schema = @Schema(oneOf = {SkinEye.class})))
    })
    @GetMapping(value = "/hazard/skin-eye/search/by-dtxsid/{dtxsid}")
    @ResponseBody
    List<SkinEye> skinEyedByDtxsid(@Parameter(required = true, description = "DSSTox Substance Identifier", example = "DTXSID0021125") @PathVariable("dtxsid") String dtxsid);

    /**
     * {@code POST  hazard/skin-eye/search/by-dtxsid/{dtxsid} : get list of skin eye data for batch "dtxsid".
     * @param dtxsid the matching dtxsid of the skin eye data to retrieve.
     * @return the {@link ResponseEntity } with status {@code 200 (OK)} and with body the list of skin eye}.
     */
    @Operation(summary = "Get data for a batch of DTXSID(s)", description = "return skin-eye data for requested DTXSIDs. Note: Maximum ${application.batch-size} DTXSIDs per request")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json",
                    schema = @Schema(oneOf = {SkinEye.class}))),
            @ApiResponse(responseCode = "400", description = "User has submitted more than allowed number (${application.batch-size}) of DTXSID(s).",
                    content = @Content(mediaType = "application/json",
                            examples = {@ExampleObject(name = "", value = "{\"title\":\"Validation Error\",\"status\":400,\"detail\":\"System supports requests of '200' DTXSIDs at one time, '202' were submitted.\"}", description = "Validation error for more then allowed number of dtxsid(s).")},
                            schema = @Schema(oneOf = {ProblemDetail.class})))
    })
    @PostMapping(value = "/hazard/skin-eye/search/by-dtxsid/")
    @ResponseBody
    List<SkinEye> skinEyedBatch(@io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "JSON array of DSSTox Substance Identifier",
            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = String.class)),
                    examples = {@ExampleObject("\"[\\\"DTXSID7020182\\\",\\\"DTXSID9020112\\\"]\"")})})
                                   @RequestBody String[] dtxsids);
}

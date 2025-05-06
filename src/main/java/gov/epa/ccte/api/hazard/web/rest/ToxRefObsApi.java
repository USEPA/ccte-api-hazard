package gov.epa.ccte.api.hazard.web.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import gov.epa.ccte.api.hazard.domain.ToxRefObs;
import gov.epa.ccte.api.hazard.web.rest.requests.ToxRefPage;

import java.util.List;

/**
 * REST controller for getting the {@link gov.epa.ccte.api.hazard.domain.ToxRefObs}s.
 */
@Tag(name = "ToxRefDB Observation Resource",
        description = "API endpoints for collecting ToxRefDB observation data.")
@SecurityRequirement(name = "api_key")
public interface ToxRefObsApi {
    /**
     * {@code GET  hazard/toxref/observations/by-study-id/{study-id} : get list of ToxRefDB observations for the "study id"
     * @param study-id the matching study-id of the ToxRefDB observations to retrieve.
     * @return the {@link ResponseEntity } with status {@code 200 (OK)} and with body the list of ToxRefDB observations}.
     */
    @Operation(summary = "Get observations by study id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json",
                    schema = @Schema(oneOf = {ToxRefObs.class})))
    })
    @GetMapping(value = "/hazard/toxref/observations/search/by-study-id/{studyId}")
    @ResponseBody
    List<ToxRefObs> toxRefObsByStudyId(@Parameter(required = true, description = "Study ID", example = "63") @PathVariable("studyId") Integer studyId);

    /**
     * {@code GET  hazard/toxref/observations/by-dtxsid/{dtxsid} : get list of ToxRefDB observations for the "dtxsid".
     * @param dtxsid the matching dtxsid of the ToxRefDB observations to retrieve.
     * @return the {@link ResponseEntity } with status {@code 200 (OK)} and with body the list of ToxRefDB observations}.
     */
    @Operation(summary = "Get observations by dtxsid")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json",
                    schema = @Schema(oneOf = {ToxRefObs.class})))
    })
    @GetMapping(value = "/hazard/toxref/observations/search/by-dtxsid/{dtxsid}")
    @ResponseBody
    List<ToxRefObs> toxRefObsByDtxsid(@Parameter(required = true, description = "dtxsid", example = "DTXSID1037806") @PathVariable("dtxsid") String dtxsid);

    /**
     * {@code GET  hazard/toxref/observations/by-study-type/{study-type} : get list of ToxRefDB observations for the "study type".
     * @param study-id the matching study-id of the ToxRefDB observations to retrieve.
     * @return the {@link ResponseEntity } with status {@code 200 (OK)} and with body the list of ToxRefDB observations }.
     */
    @Operation(summary = "Get observations by study type")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json",
                    schema = @Schema(oneOf = {ToxRefObs.class})))
    })
    @GetMapping(value = "/hazard/toxref/observations/search/by-study-type/{studyType}")
    @ResponseBody
    ToxRefPage toxRefObsByStudyType(@Parameter(required = true, description = "Study Type", example = "DEV") @PathVariable("studyType") String studyType,
									@RequestParam(required = false, value = "pageNumber", defaultValue = "1") Integer pageNumber);
}

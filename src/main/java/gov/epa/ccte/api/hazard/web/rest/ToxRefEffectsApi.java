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

import gov.epa.ccte.api.hazard.domain.ToxRefEffects;
import gov.epa.ccte.api.hazard.web.rest.requests.ToxRefPage;

import java.util.List;

/**
 * REST controller for getting the {@link gov.epa.ccte.api.hazard.domain.ToxRefEffects}s.
 */
@Tag(name = "ToxRefDb Effects Resource",
        description = "API endpoints for collecting ToxRefDB effects data.")
@SecurityRequirement(name = "api_key")
public interface ToxRefEffectsApi {
    /**
     * {@code GET  hazard/toxref/effects/by-study-id/{study-id} : get list of ToxRefDB effects information for the "study id".
     * @param study-id the matching study-id of the ToxRefDB effects information to retrieve.
     * @return the {@link ResponseEntity } with status {@code 200 (OK)} and with body the list of ToxRefDB effects information}.
     */
    @Operation(summary = "Get data by study id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json",
                    schema = @Schema(oneOf = {ToxRefEffects.class})))
    })
    @GetMapping(value = "/hazard/toxref/effects/search/by-study-id/{studyId}")
    @ResponseBody
    List<ToxRefEffects> toxRefEffectsByStudyId(@Parameter(required = true, description = "Study ID", example = "63") @PathVariable("studyId") Integer studyId);


    /**
     * {@code GET  hazard/toxref/effects/by-dtxsid/{dtxsid} : get list of ToxRefDB effects information for the "dtxsid".
     * @param dtxsid the matching dtxsid of the ToxRefDB effects information to retrieve.
     * @return the {@link ResponseEntity } with status {@code 200 (OK)} and with body the list of ToxRefDB effects information}.
     */
    @Operation(summary = "Get data by dtxsid")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json",
                    schema = @Schema(oneOf = {ToxRefEffects.class})))
    })
    @GetMapping(value = "/hazard/toxref/effects/search/by-dtxsid/{dtxsid}")
    @ResponseBody
    List<ToxRefEffects> toxRefEffectsByDtxsid(@Parameter(required = true, description = "dtxsid", example = "DTXSID1037806") @PathVariable("dtxsid") String dtxsid);


    /**
     * {@code GET  hazard/toxref/effects/by-study-type/{study-type} : get list of ToxRefDB effects information for the "study type".
     * @param study-id the matching study-id of the ToxRefDB effects information to retrieve.
     * @return the {@link ResponseEntity } with status {@code 200 (OK)} and with body the list of ToxRefDB effects information}.
     */
    @Operation(summary = "Get data by study type")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json",
                    schema = @Schema(oneOf = {ToxRefEffects.class})))
    })
    @GetMapping(value = "/hazard/toxref/effects/search/by-study-type/{studyType}")
    @ResponseBody
    ToxRefPage toxRefEffectsByStudyType(@Parameter(required = true, description = "Study Type", example = "DEV") @PathVariable("studyType") String studyType,
										@RequestParam(required = false, value = "pageNumber", defaultValue = "1") Integer pageNumber);

}

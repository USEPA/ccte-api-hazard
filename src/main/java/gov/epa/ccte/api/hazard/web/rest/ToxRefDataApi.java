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

import gov.epa.ccte.api.hazard.domain.ToxRefData;
import gov.epa.ccte.api.hazard.web.rest.requests.ToxRefPage;

import java.util.List;

/**
 * REST controller for getting the {@link gov.epa.ccte.api.hazard.domain.ToxRefData}s.
 */
@Tag(name = "ToxRefDB Data Resource",
        description = "API endpoints for collecting ToxRefDB data.")
@SecurityRequirement(name = "api_key")
public interface ToxRefDataApi {
    /**
     * {@code GET  hazard/toxref/data/by-study-id/{study-id} : get list of ToxRefDB data for the "study id"
     * @param study-id the matching study-id of the ToxRefDB data to retrieve.
     * @return the {@link ResponseEntity } with status {@code 200 (OK)} and with body the list of ToxRefDB data}.
     */
    @Operation(summary = "Get data by study id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json",
                    schema = @Schema(oneOf = {ToxRefData.class})))
    })
    @GetMapping(value = "/hazard/toxref/data/search/by-study-id/{studyId}")
    @ResponseBody
    List<ToxRefData> toxRefDataByStudyId(@Parameter(required = true, description = "Study ID", example = "63") @PathVariable("studyId") Integer studyId);

    /**
     * {@code GET  hazard/toxref/data/by-dtxsid/{dtxsid} : get list of ToxRefDB data for the "dtxsid".
     * @param dtxsid the matching dtxsid of the ToxRefDB data to retrieve.
     * @return the {@link ResponseEntity } with status {@code 200 (OK)} and with body the list of ToxRefDB data}.
     */
    @Operation(summary = "Get data by dtxsid")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json",
                    schema = @Schema(oneOf = {ToxRefData.class})))
    })
    @GetMapping(value = "/hazard/toxref/data/search/by-dtxsid/{dtxsid}")
    @ResponseBody
    List<ToxRefData> toxRefDataByDtxsid(@Parameter(required = true, description = "dtxsid", example = "DTXSID1037806") @PathVariable("dtxsid") String dtxsid);


    /**
     * {@code GET  hazard/toxref/effects/by-study-type/{study-type} : get list of ToxRefDB data for the "study type".
     * @param study-id the matching study-id of the ToxRefDB data to retrieve.
     * @return the {@link ResponseEntity } with status {@code 200 (OK)} and with body the list of ToxRefDB data }.
     */
    @Operation(summary = "Get data by study type")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json",
                    schema = @Schema(oneOf = {ToxRefData.class})))
    })
    @GetMapping(value = "/hazard/toxref/data/search/by-study-type/{studyType}")
    @ResponseBody
    ToxRefPage toxRefDataByStudyType(@Parameter(required = true, description = "Study Type", example = "DEV") @PathVariable("studyType") String studyType,
    									@RequestParam(required = false, value = "pageNumber", defaultValue = "1") Integer pageNumber);

}

package gov.epa.ccte.api.hazard.web.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import gov.epa.ccte.api.hazard.domain.ToxRefSummary;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * REST controller for getting the {@link gov.epa.ccte.api.hazard.domain.ToxRefSummary}s.
 */
@Tag(name = "ToxRefDB Summary Resource",
        description = "API endpoints for collecting ToxRefDB summary data.")
@SecurityRequirement(name = "api_key")
public interface ToxRefSummaryApi {
    /**
     * {@code GET  hazard/toxref/summary/by-study-id/{study-id} : get list of ToxRefDB data summaries for the "study id"
     * @param study-id the matching study-id of the ToxRefDB data to retrieve.
     * @return the {@link ResponseEntity } with status {@code 200 (OK)} and with body the list of ToxRefDB data summaries}.
     */
    @Operation(summary = "Get data by study id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json",
                    schema = @Schema(oneOf = {ToxRefSummary.class})))
    })
    @GetMapping(value = "/hazard/toxref/summary/search/by-study-id/{studyId}")
    @ResponseBody
    List<ToxRefSummary> toxRefSummaryByStudyId(@Parameter(required = true, description = "Study ID", example = "63") @PathVariable("studyId") Integer studyId);

    /**
     * {@code GET  hazard/toxref/summary/by-dtxsid/{dtxsid} : get list of ToxRefDB data summaries for the "dtxsid".
     * @param dtxsid the matching dtxsid of the ToxRefDB data to retrieve.
     * @return the {@link ResponseEntity } with status {@code 200 (OK)} and with body the list of ToxRefDB data summaries}.
     */
    @Operation(summary = "Get data by dtxsid")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json",
                    schema = @Schema(oneOf = {ToxRefSummary.class})))
    })
    @GetMapping(value = "/hazard/toxref/summary/search/by-dtxsid/{dtxsid}")
    @ResponseBody
    List<ToxRefSummary> toxRefSummaryByDtxsid(@Parameter(required = true, description = "dtxsid", example = "DTXSID1037806") @PathVariable("dtxsid") String dtxsid);


    /**
     * {@code GET  hazard/toxref/summary/by-study-type/{study-type} : get list of ToxRefDB data summaries for the "study type".
     * @param study-id the matching study-id of the ToxRefDB data to retrieve.
     * @return the {@link ResponseEntity } with status {@code 200 (OK)} and with body the list of ToxRefDB data summaries}.
     */
    @Operation(summary = "Get data by study type")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json",
                    schema = @Schema(oneOf = {ToxRefSummary.class})))
    })
    @GetMapping(value = "/hazard/toxref/summary/search/by-study-type/{studyType}")
    @ResponseBody
    List<ToxRefSummary> toxRefSummaryByStudyType(@Parameter(required = true, description = "Study Type", example = "DEV") @PathVariable("studyType") String studyType);

}

package gov.epa.ccte.api.hazard.web.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import gov.epa.ccte.api.hazard.domain.Pprtv;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * REST controller for getting the {@link gov.epa.ccte.api.hazard.domain.Pprtv}s.
 */
@Tag(name = "PPRTV Resource",
        description = "API endpoints for collecting pprtv chemical data for specified chemical identifier (DTXSID).")
@SecurityRequirement(name = "api_key")
public interface PprtvApi {
    /**
     * {@code GET  hazard/pprtv/search/by-dtxsid/{dtxsid} : get list of pprtv chemical data for the "dtxsid".
     * @param dtxsid the matching dtxsid of the pprtv chemical data to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the list of hazard}.
     */
    @Operation(summary = "Get all pprtv chemical data by dtxsid")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json",
                    schema = @Schema(oneOf = { Pprtv.class})))
    })
    @GetMapping("hazard/pprtv/search/by-dtxsid/{dtxsid}")
    @ResponseBody
    List<Pprtv> pprtvChemicalsByDtxsid(@Parameter(required = true, description = "DSSTox Substance Identifier", example = "DTXSID7020182")
                                   @PathVariable("dtxsid") String dtxsid);
}

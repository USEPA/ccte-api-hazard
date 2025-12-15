package gov.epa.ccte.api.hazard.web.rest;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import gov.epa.ccte.api.hazard.domain.Hawc;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * REST controller for getting the {@link gov.epa.ccte.api.hazard.domain.Hawc}s.
 */
@Tag(name = "HAWC Resource",
        description = "Endpoint used by the populate CompTox Chemicals Dashboard (CCD) Literature> EPA HAWC tab. Health Assessment Workspace Collaborative (HAWC) is an interactive, expert-driven, content management system for human health assessments that is intended to promote transparency, data usability, and understanding of the data and decisions supporting an environmental and human health assessment. Links will be only be available for public assessments in HAWC.")
@SecurityRequirement(name = "api_key")
public interface HawcApi {

    /**
     * {@code GET  hazard/hawc/search/by-dtxsid/{dtxsid} : get CCD - EPA HAWC link mapper for the "dtxsid".
     * @param dtxsid the matching dtxsid of the CCD - EPA HAWC link mapper to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the list of CCD - EPA HAWC link mapper}.
     */
    @Operation(summary = "Get HAWC link by DTXSID", description = "return HAWC link for requested DTXSID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json",
                    schema = @Schema(oneOf = { Hawc.class}))),
    })
    @RequestMapping(value = "hazard/hawc/search/by-dtxsid/{dtxsid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    List<Hawc> hawcDataByDtxsid(@Parameter(required = true, description = "DSSTox Substance Identifier", example = "DTXSID7020182") 
    						@PathVariable("dtxsid") String dtxsid);
}

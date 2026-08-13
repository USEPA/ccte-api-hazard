package gov.epa.ccte.api.hazard.web.rest;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import gov.epa.ccte.api.hazard.domain.Adme;
import gov.epa.ccte.api.hazard.projection.CcdADME;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * REST controller for getting the {@link gov.epa.ccte.api.hazard.domain.Adme}s.
 */
@Tag(name = "ADME - IVIVE Resource",
        description = "Endpoint for in-vitro to in-vivo extrapolation (IVIVE) to predict a chemical's Absorption, Distribution, Metabolism, and Excretion (ADME) properties as provided on the CompTox Chemicals Dashboard's ADME>IVIVE tab")
@SecurityRequirement(name = "api_key")
public interface AdmeApi {
	
    /**
     * {@code GET  hazard/adme-ivive/search/by-dtxsid/{dtxsid} : get list of adme - ivive data for the "dtxsid".
     * @param dtxsid the matching dtxsid of the adme - ivive data to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the list of hazard}.
     */
    @Operation(summary = "Get ADME data for IVIVE by DTXSID with CCD projection",
  		   description = "return ADME data for requested DTXSID. There is an available projection aligned with what's available on the CCD ADME> IVIVE tab: ccd-adme-data" +
                   "If no projection is specified, the default ADME-IVIVE projection is returned")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json",
                    schema = @Schema(oneOf = { Adme.class, CcdADME.class}))),
    })
    @GetMapping(value = "hazard/adme-ivive/search/by-dtxsid/{dtxsid}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<?> admeDataByDtxsid(@Parameter(required = true, description = "DSSTox Substance Identifier", example = "DTXSID7020182") 
    						@PathVariable("dtxsid") String dtxsid,
    						@Parameter(description = "Specifies if projection is used. Option: ccd-adme-data. " +
    								"If omitted, the default ADME-IVIVE projection is returned.")
    						@RequestParam(value = "projection", required = false) String projection);
    
}

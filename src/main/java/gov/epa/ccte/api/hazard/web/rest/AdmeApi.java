package gov.epa.ccte.api.hazard.web.rest;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
        description = "API endpoints for collecting adme - ivive data for specified chemical identifier (DTXSID).")
@SecurityRequirement(name = "api_key")
public interface AdmeApi {
	
    /**
     * {@code GET  hazard/adme-ivive/search/by-dtxsid/{dtxsid} : get list of adme - ivive data for the "dtxsid".
     * @param dtxsid the matching dtxsid of the adme - ivive data to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the list of hazard}.
     */
    @Operation(summary = "Get all adme - ivive data by dtxsid with ccd projection",
  		   description = "return adme - ivive data for requested dtxsid" +
                   "there is an available projection for ccd ADME - IVIVE page:" +
			         "ccd-adme-data" +
                   "If no projection is specified, the default ADME data will be returned")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json",
                    schema = @Schema(oneOf = { Adme.class, CcdADME.class}))),
    })
    @RequestMapping(value = "hazard/adme-ivive/search/by-dtxsid/{dtxsid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    List<?> admeDataByDtxsid(@Parameter(required = true, description = "DSSTox Substance Identifier", example = "DTXSID7020182") 
    						@PathVariable("dtxsid") String dtxsid,
    						@Parameter(description = "Specifies if projection is used. Option: ccd-adme-data, " +
    								"If omitted, the default ADME-IVIVE data is returned.")
    						@RequestParam(value = "projection", required = false) String projection);
    
}

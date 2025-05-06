package gov.epa.ccte.api.hazard.web.rest;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import gov.epa.ccte.api.hazard.domain.Adme;
import gov.epa.ccte.api.hazard.repository.AdmeRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
public class AdmeResource implements AdmeApi{
    private final AdmeRepository repository;
    
    public AdmeResource(AdmeRepository repository) {
        this.repository = repository;
    }
    
    @Override
    public List<?> admeDataByDtxsid(String dtxsid, String projection) {
        log.debug("all ADME - IVIVE data = {}", dtxsid);

        if (projection == null || projection.isEmpty()) {
            List<Adme> result = repository.findByDtxsid(dtxsid, Adme.class);
            return result != null ? List.of(result) : List.of(); 
        }

        Object result = switch (projection) {
        	case "ccd-adme-data" -> repository.findByDtxsidWithLabelColumn(dtxsid);
        	default -> repository.findByDtxsid(dtxsid, Adme.class);
        };

        if (result instanceof List<?>) {
            return (List<?>) result;
        } else if (result != null) {
            return List.of(result); 
        } else {
            return List.of(); 
        }
    }
}

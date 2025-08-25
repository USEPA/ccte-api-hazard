package gov.epa.ccte.api.hazard.web.rest;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import gov.epa.ccte.api.hazard.domain.Iris;
import gov.epa.ccte.api.hazard.repository.IrisRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
public class IrisResource implements IrisApi{

    private final IrisRepository repository;
    
    public IrisResource(IrisRepository repository) {
        this.repository = repository;
    }
    
    @Override
    public List<Iris> irisDataByDtxsid(String dtxsid) {
        log.debug("all IRIS data = {}", dtxsid);
        
        List<Iris> data = repository.findByDtxsid(dtxsid);
        
        return data;
    }
    
}

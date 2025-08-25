package gov.epa.ccte.api.hazard.web.rest;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import gov.epa.ccte.api.hazard.domain.Hawc;
import gov.epa.ccte.api.hazard.repository.HawcRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
public class HawcResource implements HawcApi{

    private final HawcRepository repository;
    
    public HawcResource(HawcRepository repository) {
        this.repository = repository;
    }
    
    @Override
    public List<Hawc> hawcDataByDtxsid(String dtxsid) {
        log.debug("CCD - EPA HAWC link mapper for dtxsid = {}", dtxsid);
        
        List<Hawc> data = repository.findByDtxsid(dtxsid);
        
        return data;
    }
}

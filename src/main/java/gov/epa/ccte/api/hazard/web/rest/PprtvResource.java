package gov.epa.ccte.api.hazard.web.rest;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import gov.epa.ccte.api.hazard.domain.Pprtv;
import gov.epa.ccte.api.hazard.repository.PprtvRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
public class PprtvResource implements PprtvApi{

    private final PprtvRepository repository;
    
    public PprtvResource(PprtvRepository repository) {
        this.repository = repository;
    }
    
    @Override
    public @ResponseBody
    List<Pprtv> pprtvChemicalsByDtxsid(String dtxsid) {
        log.debug("all PPRTV Chemical data = {}", dtxsid);

        List<Pprtv> data = repository.findAllByDtxsid(dtxsid);

        return data;
    }
}

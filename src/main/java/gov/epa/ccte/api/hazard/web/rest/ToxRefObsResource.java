package gov.epa.ccte.api.hazard.web.rest;

import gov.epa.ccte.api.hazard.domain.ToxRefObs;
import gov.epa.ccte.api.hazard.repository.ToxRefObsRepository;
import gov.epa.ccte.api.hazard.service.ToxRefService;
import gov.epa.ccte.api.hazard.web.rest.requests.ToxRefPage;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Slf4j
@RestController
@CrossOrigin(origins = "*")
public class ToxRefObsResource implements ToxRefObsApi {
	
    private final ToxRefObsRepository repository;
    private final ToxRefService service;

    public ToxRefObsResource(ToxRefObsRepository repository, ToxRefService service) {
        this.repository = repository;
        this.service = service;
    }

    @Override
    public @ResponseBody
    List<ToxRefObs> toxRefObsByStudyId(Integer studyId) {
        log.debug("all Tox Ref Observations by Study ID = {}", studyId);

        List<ToxRefObs> data = repository.findAllByStudyId(studyId, ToxRefObs.class);

        return data;
    }

    @Override
    public @ResponseBody
    List<ToxRefObs> toxRefObsByDtxsid( String dtxsid) {
        log.debug("all Tox Ref Observations by DTXSID  = {}", dtxsid);

        List<ToxRefObs> data = repository.findAllByDtxsid(dtxsid, ToxRefObs.class);

        return data;
    }

	@Override
	public ToxRefPage toxRefObsByStudyType(String studyType, Integer pageNumber){
        log.debug("all Tox Ref Observations by Study Type = {}", studyType);
        Integer pageSize = 10000;
        Pageable pageable = PageRequest.of(pageNumber-1, pageSize);
        return service.getAllToxRefObsByStudyType(studyType, pageSize, pageNumber, pageable);
	}
}

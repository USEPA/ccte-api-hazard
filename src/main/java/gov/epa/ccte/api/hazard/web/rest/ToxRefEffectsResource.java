package gov.epa.ccte.api.hazard.web.rest;

import gov.epa.ccte.api.hazard.domain.ToxRefEffects;
import gov.epa.ccte.api.hazard.repository.ToxRefEffectsRepository;
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
public class ToxRefEffectsResource implements ToxRefEffectsApi {

    private final ToxRefEffectsRepository repository;
    private final ToxRefService service;


    public ToxRefEffectsResource(ToxRefEffectsRepository repository, ToxRefService service) {
        this.repository = repository;
        this.service = service;
    }

    @Override
    public @ResponseBody
    List<ToxRefEffects> toxRefEffectsByStudyId(Integer studyId) {
        log.debug("all Tox Ref Effects by Study ID = {}", studyId);

        List<ToxRefEffects> data = repository.findAllByStudyId(studyId, ToxRefEffects.class);

        return data;
    }


    @Override
    public @ResponseBody
    List<ToxRefEffects> toxRefEffectsByDtxsid(String dtxsid) {
        log.debug("all Tox Ref Effects by DTXSID = {}", dtxsid);

        List<ToxRefEffects> data = repository.findAllByDtxsid(dtxsid, ToxRefEffects.class);

        return data;
    }    


	@Override
	public ToxRefPage toxRefEffectsByStudyType(String studyType, Integer pageNumber){
        log.debug("all Tox Ref Effects by Study Type = {}", studyType);
        Integer pageSize = 10000;
        Pageable pageable = PageRequest.of(pageNumber-1, pageSize);
        return service.getAllToxRefEffectsByStudyType(studyType, pageSize, pageNumber, pageable);
	}
}


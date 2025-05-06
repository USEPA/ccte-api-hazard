package gov.epa.ccte.api.hazard.web.rest;

import gov.epa.ccte.api.hazard.domain.ToxRefData;
import gov.epa.ccte.api.hazard.repository.ToxRefDataRepository;
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
public class ToxRefDataResource implements ToxRefDataApi {
    private final ToxRefDataRepository repository;
    private final ToxRefService service;

    public ToxRefDataResource(ToxRefDataRepository repository, ToxRefService service) {
        this.repository = repository;
        this.service = service;
    }

    @Override
    public @ResponseBody
    List<ToxRefData> toxRefDataByStudyId(Integer studyId) {
        log.debug("all Tox Ref Data by Study ID = {}", studyId);

        List<ToxRefData> data = repository.findAllByStudyId(studyId, ToxRefData.class);

        return data;
    }


    @Override
    public @ResponseBody
    List<ToxRefData> toxRefDataByDtxsid(String dtxsid) {
        log.debug("all Tox Ref Data by DTXSID = {}", dtxsid);

        List<ToxRefData> data = repository.findAllByDtxsid(dtxsid, ToxRefData.class);

        return data;
    }    

	@Override
	public ToxRefPage toxRefDataByStudyType(String studyType, Integer pageNumber){
        log.debug("all Tox Ref Data by Study Type = {}", studyType);
        Integer pageSize = 10000;
        Pageable pageable = PageRequest.of(pageNumber-1, pageSize);
        return service.getAllToxRefDataByStudyType(studyType, pageSize, pageNumber, pageable);
	}
	
}

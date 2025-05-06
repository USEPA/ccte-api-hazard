package gov.epa.ccte.api.hazard.web.rest;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import gov.epa.ccte.api.hazard.domain.ToxRefSummary;
import gov.epa.ccte.api.hazard.repository.ToxRefSummaryRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
public class ToxRefSummaryResource implements ToxRefSummaryApi{
	
    private final ToxRefSummaryRepository repository;

    public ToxRefSummaryResource(ToxRefSummaryRepository repository) {
        this.repository = repository;
    }

    @Override
    public @ResponseBody
    List<ToxRefSummary> toxRefSummaryByStudyId(Integer studyId) {
        log.debug("all Tox Ref Data by Study ID = {}", studyId);

        List<ToxRefSummary> data = repository.findAllByStudyId(studyId, ToxRefSummary.class);

        return data;
    }


    @Override
    public @ResponseBody
    List<ToxRefSummary> toxRefSummaryByDtxsid(String dtxsid) {
        log.debug("all Tox Ref Data by DTXSID = {}", dtxsid);

        List<ToxRefSummary> data = repository.findAllByDtxsid(dtxsid, ToxRefSummary.class);

        return data;
    }    


    @Override
    public @ResponseBody
    List<ToxRefSummary> toxRefSummaryByStudyType(String studyType) {
        log.debug("all Tox Ref Data by Study Type = {}", studyType);

        List<ToxRefSummary> data = repository.findAllByStudyType(studyType, ToxRefSummary.class);

        return data;
    }
}

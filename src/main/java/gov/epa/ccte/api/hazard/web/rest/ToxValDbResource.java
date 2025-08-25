package gov.epa.ccte.api.hazard.web.rest;


import gov.epa.ccte.api.hazard.domain.ToxValDb;
import gov.epa.ccte.api.hazard.repository.ToxValDbRepository;
import gov.epa.ccte.api.hazard.web.rest.error.HigherNumberOfDtxsidException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
public class ToxValDbResource implements ToxValDbApi {
    private final ToxValDbRepository repository;

    @Value("${application.batch-size}")
    private Integer batchSize;

    public ToxValDbResource(ToxValDbRepository repository) {
        this.repository = repository;
    }


    @Override
    public @ResponseBody
    List<ToxValDb> hazardByDtxsid(String dtxsid) {
        log.debug("all ToxValDb for dtxsid = {}", dtxsid);

        List<ToxValDb> data = repository.findAllByDtxsid(dtxsid,  ToxValDb.class);
        log.debug("data size = {}", data.size());

        return data;
    }

    @Override
    public @ResponseBody
    List<ToxValDb> hazardBatch(String[] dtxsids) {

        log.debug("all ToxValDb for dtxsid size = {}", dtxsids.length);

        if(dtxsids.length > batchSize)
            throw new HigherNumberOfDtxsidException(dtxsids.length, batchSize);

        List<ToxValDb> data = repository.findByDtxsidInOrderByDtxsidAsc(dtxsids, ToxValDb.class);
        log.debug("data size = {}", data.size());

        return data;
    }

}

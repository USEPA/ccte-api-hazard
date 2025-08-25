package gov.epa.ccte.api.hazard.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import gov.epa.ccte.api.hazard.domain.ToxRefBatch;
import gov.epa.ccte.api.hazard.repository.ToxRefBatchRepository;
import gov.epa.ccte.api.hazard.web.rest.error.HigherNumberOfDtxsidException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
public class ToxRefBatchResource implements ToxRefBatchApi{

    private final ToxRefBatchRepository repository;
    @Value("${application.batch-size}")
    private Integer batchSize;

    public ToxRefBatchResource(ToxRefBatchRepository repository) {
        this.repository = repository;
    }

    @Override
    public @ResponseBody
    List<ToxRefBatch> toxRefBatch( String[] dtxsids) {
        log.debug("all ToxRef data for dtxsid batch size = {}", dtxsids.length);

        if(dtxsids.length > batchSize)
            throw new HigherNumberOfDtxsidException(dtxsids.length, batchSize);

        List<ToxRefBatch> data = repository.findByDtxsidInOrderByDtxsidAsc(dtxsids, ToxRefBatch.class);

        return data;
    }
}

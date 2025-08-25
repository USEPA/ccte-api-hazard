package gov.epa.ccte.api.hazard.web.rest;

import gov.epa.ccte.api.hazard.domain.CancerSummary;
import gov.epa.ccte.api.hazard.repository.CancerSummaryRepository;
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
public class CancerSummaryResource implements CancerSummaryApi {
    private final CancerSummaryRepository repository;

    @Value("${application.batch-size}")
    private Integer batchSize;

    public CancerSummaryResource(CancerSummaryRepository repository) {
        this.repository = repository;
    }

    @Override
    public @ResponseBody
    List<CancerSummary> cancerSummaryByDtxsid(String dtxsid) {
        log.debug("all cancer summary for dtxsid = {}", dtxsid);

        List<CancerSummary> data = repository.findAllByDtxsid(dtxsid, CancerSummary.class);

        return data;
    }

    @Override
    public @ResponseBody
    List<CancerSummary> cancerSummaryBatch(String[] dtxsids) {
        log.debug("all cancer summary for dtxsid size = {}", dtxsids.length);

        if(dtxsids.length > batchSize)
            throw new HigherNumberOfDtxsidException(dtxsids.length, batchSize);

        List<CancerSummary> data = repository.findByDtxsidInOrderByDtxsidAsc(dtxsids, CancerSummary.class);

        return data;
    }

}

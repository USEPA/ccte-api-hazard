package gov.epa.ccte.api.hazard.web.rest;

import gov.epa.ccte.api.hazard.domain.GenetoxDetail;
import gov.epa.ccte.api.hazard.domain.GenetoxSummary;
import gov.epa.ccte.api.hazard.repository.GenetoxDetailRepository;
import gov.epa.ccte.api.hazard.repository.GenetoxSummaryRepository;
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
public class GenetoxResource implements GenetoxApi {
    private final GenetoxDetailRepository detailRepository;
    private final GenetoxSummaryRepository summaryRepository;

    @Value("${application.batch-size}")
    private Integer batchSize;

    public GenetoxResource(GenetoxDetailRepository repository, GenetoxSummaryRepository summaryRepository) {
        this.detailRepository = repository;
        this.summaryRepository = summaryRepository;
    }

// *********************** Summary - start *************************************

    @Override
    public @ResponseBody
    List<GenetoxSummary> genetoxSummaryByDtxsid(String dtxsid) {
        log.debug("all Genetox Summaries for dtxsid = {}", dtxsid);

        List<GenetoxSummary> data = summaryRepository.findByDtxsid(dtxsid, GenetoxSummary.class);

        return data;
    }

    @Override
    public @ResponseBody
    List<GenetoxSummary>batchSearchSummary(String[] dtxsids) {
        log.debug("all cancer summary for dtxsid size = {}", dtxsids.length);

        if(dtxsids.length > batchSize)
            throw new HigherNumberOfDtxsidException(dtxsids.length, batchSize);

        List<GenetoxSummary> data = summaryRepository.findByDtxsidInOrderByDtxsidAsc(dtxsids, GenetoxSummary.class);

        return data;
    }

// *********************** Summary - End *************************************

// *********************** Detail - start *************************************

    @Override
    public List<?> getGenetoxDetailsByDtxsid(String dtxsid, String projection) {
        log.debug("all Genetox Details for dtxsid = {}", dtxsid);
        
        if (projection == null || projection.isEmpty()) {
            List<GenetoxDetail> result = detailRepository.findByDtxsidOrderBySourceAsc(dtxsid, GenetoxDetail.class);
            return result != null ? List.of(result) : List.of(); 
        }
        
        Object result = switch (projection) {
        	case "ccd-genetox-details" -> detailRepository.findByDtxsidWithConcatenatedColumn(dtxsid);
        	default -> detailRepository.findByDtxsidOrderBySourceAsc(dtxsid, GenetoxDetail.class);
        };
        
        if (result instanceof List<?>) {
            return (List<?>) result;
        } else if (result != null) {
            return List.of(result); 
        } else {
            return List.of(); 
        }
    }


    @Override
    public @ResponseBody
    List<GenetoxDetail> batchSearch(String[] dtxsids) {
        log.debug("all cancer summary for dtxsid size= {}", dtxsids.length);

        if(dtxsids.length > batchSize)
            throw new HigherNumberOfDtxsidException(dtxsids.length, batchSize);

        List<GenetoxDetail> data = detailRepository.findByDtxsidInOrderByDtxsidAsc(dtxsids, GenetoxDetail.class);

        return data;
    }
}

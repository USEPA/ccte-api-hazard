package gov.epa.ccte.api.hazard.web.rest;

import gov.epa.ccte.api.hazard.domain.SkinEye;
import gov.epa.ccte.api.hazard.repository.SkinEyeRepository;
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
public class SkinEyeResource implements SkinEyeApi {

    private final SkinEyeRepository repository;
    @Value("${application.batch-size}")
    private Integer batchSize;

    public SkinEyeResource(SkinEyeRepository repository) {
        this.repository = repository;
    }

    @Override
    public @ResponseBody
    List<SkinEye> skinEyedByDtxsid(String dtxsid) {
        log.debug("all skin eye for dtxsid = {}", dtxsid);

        List<SkinEye> data = repository.findAllByDtxsid(dtxsid, SkinEye.class);

        return data;
    }

    @Override
    public @ResponseBody
    List<SkinEye> skinEyedBatch( String[] dtxsids) {
        log.debug("all skin eye for dtxsid size = {}", dtxsids.length);

        if(dtxsids.length > batchSize)
            throw new HigherNumberOfDtxsidException(dtxsids.length, batchSize);

        List<SkinEye> data = repository.findByDtxsidInOrderByDtxsidAsc(dtxsids, SkinEye.class);

        return data;
    }
}

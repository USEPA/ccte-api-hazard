package gov.epa.ccte.api.hazard.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import gov.epa.ccte.api.hazard.domain.ToxRefData;
import gov.epa.ccte.api.hazard.domain.ToxRefEffects;
import gov.epa.ccte.api.hazard.domain.ToxRefObs;
import gov.epa.ccte.api.hazard.repository.ToxRefDataRepository;
import gov.epa.ccte.api.hazard.repository.ToxRefEffectsRepository;
import gov.epa.ccte.api.hazard.repository.ToxRefObsRepository;
import gov.epa.ccte.api.hazard.web.rest.requests.ToxRefPage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ToxRefService {

    private final ToxRefDataRepository dataRepository;
    private final ToxRefEffectsRepository effectsRepository;
    private final ToxRefObsRepository obsRepository;
    
    public ToxRefService(ToxRefDataRepository dataRepository, ToxRefEffectsRepository effectsRepository, ToxRefObsRepository obsRepository) {
        this.dataRepository = dataRepository;
        this.effectsRepository = effectsRepository;
        this.obsRepository = obsRepository;
    }
    
    public ToxRefPage getAllToxRefDataByStudyType(String studyType, Integer pageSize, Integer pageNumber, Pageable pageable) {
        log.debug("study type: " + studyType);

        Page<ToxRefData> data = dataRepository.findAllByStudyTypeOrderByStudyIdAsc(studyType, pageable);
        log.debug("data size: {}", data.getTotalElements());        
        ToxRefPage results = ToxRefPage.builder()
        	   .studyType(studyType)
               .data(data.getContent())
               .recordsOnPage(data.getNumberOfElements())
               .totalRecords(data.getTotalElements())
               .pageNumber(pageNumber)
               .totalPages((int) Math.ceil(data.getTotalElements()/pageSize)+1)
               .build();
        return results;
    }
    
    public ToxRefPage getAllToxRefEffectsByStudyType(String studyType, Integer pageSize, Integer pageNumber, Pageable pageable) {
        log.debug("study type: " + studyType);

        Page<ToxRefEffects> data = effectsRepository.findAllByStudyTypeOrderByStudyIdAsc(studyType, pageable);
        log.debug("data size: {}", data.getTotalElements());        
        ToxRefPage results = ToxRefPage.builder()
        	   .studyType(studyType)
               .data(data.getContent())
               .recordsOnPage(data.getNumberOfElements())
               .totalRecords(data.getTotalElements())
               .pageNumber(pageNumber)
               .totalPages((int) Math.ceil(data.getTotalElements()/pageSize)+1)
               .build();
        return results;
    }
    
    public ToxRefPage getAllToxRefObsByStudyType(String studyType, Integer pageSize, Integer pageNumber, Pageable pageable) {
        log.debug("study type: " + studyType);

        Page<ToxRefObs> data = obsRepository.findAllByStudyTypeOrderByStudyIdAsc(studyType, pageable);
        log.debug("data size: {}", data.getTotalElements());        
        ToxRefPage results = ToxRefPage.builder()
        	   .studyType(studyType)
               .data(data.getContent())
               .recordsOnPage(data.getNumberOfElements())
               .totalRecords(data.getTotalElements())
               .pageNumber(pageNumber)
               .totalPages((int) Math.ceil(data.getTotalElements()/pageSize)+1)
               .build();
        return results;
    }
}

package gov.epa.ccte.api.hazard.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import gov.epa.ccte.api.hazard.domain.ToxRefObs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SuppressWarnings("unused")
@RepositoryRestResource(exported = false)
public interface ToxRefObsRepository extends JpaRepository<ToxRefObs, Integer> {
	
    @Transactional(readOnly = true)
    <T>List<T> findAllByStudyId(@Param("study_id") Integer studyId, Class<T> type);

    @Transactional(readOnly = true)
    <T>List<T> findAllByDtxsid(@Param("dtxsid") String dtxsid, Class<T> type);

    @Transactional(readOnly = true)
    Page<ToxRefObs> findAllByStudyTypeOrderByStudyIdAsc(@Param("study_type") String studyType, Pageable pageable);

}

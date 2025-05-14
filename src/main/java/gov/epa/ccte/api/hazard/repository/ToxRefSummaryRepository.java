package gov.epa.ccte.api.hazard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import gov.epa.ccte.api.hazard.domain.ToxRefSummary;

@RepositoryRestResource(exported = false)
public interface ToxRefSummaryRepository extends JpaRepository<ToxRefSummary, Long>{

    @Transactional(readOnly = true)
    <T>
    List<T> findAllByStudyId(@Param("study_id") Integer studyId, Class<T> type);

    @Transactional(readOnly = true)
    <T>
    List<T> findAllByDtxsid(@Param("dtxsid") String dtxsid, Class<T> type);

    @Transactional(readOnly = true)
    <T>
    List<T> findAllByStudyType(@Param("study_type") String studyType, Class<T> type);
}

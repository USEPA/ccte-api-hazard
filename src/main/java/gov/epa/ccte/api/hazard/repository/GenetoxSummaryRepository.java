package gov.epa.ccte.api.hazard.repository;

import gov.epa.ccte.api.hazard.domain.GenetoxSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SuppressWarnings("unused")
@RepositoryRestResource(exported = false)
public interface GenetoxSummaryRepository extends JpaRepository<GenetoxSummary, Integer> {
    <T> List<T> findByDtxsidInOrderByDtxsidAsc(String[] dtxsids, Class<T> type);
    @Transactional(readOnly = true)
    <T>
    List<T> findByDtxsid(String dtxsid, Class<T> type);

}
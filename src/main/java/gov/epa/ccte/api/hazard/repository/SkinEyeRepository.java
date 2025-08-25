package gov.epa.ccte.api.hazard.repository;

import gov.epa.ccte.api.hazard.domain.SkinEye;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SuppressWarnings("unused")
@RepositoryRestResource(exported = false)
public interface SkinEyeRepository extends JpaRepository<SkinEye, Integer> {
    @Transactional(readOnly = true)
    <T>
    List<T> findAllByDtxsid(@Param("dtxsid") String dtxsid, Class<T> type);

    @Transactional(readOnly = true)
    <T> List<T> findByDtxsidInOrderByDtxsidAsc(String[] dtxsids, Class<T> type);


}

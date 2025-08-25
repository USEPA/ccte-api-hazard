package gov.epa.ccte.api.hazard.repository;

import gov.epa.ccte.api.hazard.domain.ToxValDb;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@SuppressWarnings("unused")
@RepositoryRestResource(exported = false)
public interface ToxValDbRepository extends JpaRepository<ToxValDb, Integer> {

    @Transactional(readOnly = true)
    <T>List<T> findAllByDtxsid(String dtxsid, Class<T> type);
    
    @Transactional(readOnly = true)
    <T>List<T> findByDtxsidInOrderByDtxsidAsc(String[] dtxsids, Class<T> type);


    // Needed for data layer unit tests
    List findAll();
}

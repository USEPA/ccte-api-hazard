package gov.epa.ccte.api.hazard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import gov.epa.ccte.api.hazard.domain.Pprtv;


@SuppressWarnings("unused")
@RepositoryRestResource(exported = false)
public interface PprtvRepository extends JpaRepository<Pprtv, Integer> {

    @Transactional(readOnly = true)
    <T>List<T> findAllByDtxsid(String dtxsid);
}

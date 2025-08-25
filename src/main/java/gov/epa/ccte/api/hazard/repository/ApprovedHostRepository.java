package gov.epa.ccte.api.hazard.repository;


import gov.epa.ccte.api.hazard.domain.ApprovedHost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface ApprovedHostRepository extends JpaRepository<ApprovedHost, Integer> {
}
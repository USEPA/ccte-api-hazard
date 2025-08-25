package gov.epa.ccte.api.hazard.repository;

import gov.epa.ccte.api.hazard.domain.GenetoxDetail;
import gov.epa.ccte.api.hazard.projection.CcdGenetoxDetail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SuppressWarnings("unused")
@RepositoryRestResource(exported = false)
public interface GenetoxDetailRepository extends JpaRepository<GenetoxDetail, Integer> {
    @Transactional(readOnly = true)
    <T> List<T> findByDtxsidOrderBySourceAsc(String dtxsid, Class<T> type);

    <T> List<T> findByDtxsidInOrderByDtxsidAsc(String[] dtxsids, Class<T> type);

    @Query(value = """
			SELECT
    			genetox.dtxsid,
			    genetox.source, 
			    genetox.assay_category AS assayCategory,
			    genetox.metabolic_activation AS metabolicActivation,
			    genetox.species,
			    genetox.strain,
			    genetox.year,
			    genetox.assay_result AS assayResult,
                CONCAT(CONCAT(genetox.assay_type, ' | '), genetox.assay_type_standard) AS assayType
			FROM
			    toxval.mv_genetox_details genetox     					  					
			WHERE
			    genetox.dtxsid = :dtxsid
		""", nativeQuery = true)
    List<CcdGenetoxDetail> findByDtxsidWithConcatenatedColumn(@Param("dtxsid")String dtxsid);
}

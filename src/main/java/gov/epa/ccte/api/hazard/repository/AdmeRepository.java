package gov.epa.ccte.api.hazard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import gov.epa.ccte.api.hazard.domain.Adme;
import gov.epa.ccte.api.hazard.projection.CcdADME;

@SuppressWarnings("unused")
@RepositoryRestResource(exported = false)
public interface AdmeRepository extends JpaRepository<Adme, Integer>{

    @Transactional(readOnly = true)
    <T>List<T> findByDtxsid(String dtxsid, Class<T> type);
    
    @Query(value = """
			SELECT
			    adme.dtxsid,
			    adme.measured,
			    adme.predicted,
			    adme.unit,
			    adme.model,
			    adme.reference,
			    adme.percentile,
			    adme.species,
			    adme.data_source_species,
			CASE 
			 	WHEN unit = 'uL/min/million hepatocytes' THEN 'Intrinsic Hepatic Clearance' 
			 	WHEN unit = 'mg/L' THEN 'Steady-State Plasma Concentration' 
			    WHEN unit = 'L/kg' THEN 'Volume of Distribution' 
                WHEN unit = 'hours' THEN 'PK Half Life' 
                WHEN unit = 'Days' THEN 'Days to Steady State' 
                ELSE 'Fraction Unbound in Plasma'
            END AS label,
            CASE 
			 	WHEN unit = 'uL/min/million hepatocytes' THEN 'Intrinsic hepatic clearance characterizes the volume of blood cleared by the metabolism of a million hepatocytes. This value can be scaled to predict in vivo clearance using the cellular density of, volume of, and blood flow to the liver.'
			 	WHEN unit = 'mg/L' THEN 'The plasma concentration achieved when the chemical intake is in dynamic equilibrium with its elimination for the model indicated by column “Model”. The value reported is calculated assuming a 1 mg/kg/day rate of exposure. A distribution is calculated characterizing population variability and uncertainty -- the column “Percentile” indicates from where in the distribution this value is drawn. The value can be scaled linearly to estimate a steady-state plasma concentration at any given dose for the species indicated by column “Species”.'    
			    WHEN unit = 'L/kg' THEN 'The apparent volume (L/kg) required to account for all of the chemical in the body if the concentration in all of the tissues is the same as the plasma concentration. This parameter is an indicator of the extent of chemical distribution into body fluids and tissues.'  
                WHEN unit = 'hours' THEN 'The length of time required for a plasma chemical concentration to decrease by half.'
                WHEN unit = 'Days' THEN 'Number of days of chemical exposure, given an assumed exposure scenario, required to reach the point where the chemical intake is in the dynamic equilibrium with its elimination. This in effect will result in a systematic concentration that is relatively unchanged over time.'
                ELSE 'Fraction of chemical present in the soluble portion of the plasma, not bound to plasma proteins.'
            END AS description
    		FROM
			    adme.mv_ivive adme     					  					
			WHERE
			    adme.dtxsid = :dtxsid
		""", nativeQuery = true)
    	List<CcdADME> findByDtxsidWithLabelColumn(@Param("dtxsid")String dtxsid);
    
}

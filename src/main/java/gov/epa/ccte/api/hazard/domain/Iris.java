package gov.epa.ccte.api.hazard.domain;



import java.sql.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Schema(description = "Integrated Risk Information System (IRIS): EPA's IRIS Program identifies and characterizes the health hazards of chemicals found in the environment. Each IRIS assessment can cover a chemical, a group of related chemicals, or a complex mixture. IRIS assessments are an important source of toxicity information used by EPA, state and local health agencies, other federal agencies, and international health organizations.")
@Table(name = "iris_data", schema = "iris")
public class Iris {

	@Id
    @NotNull
	@Column(name = "dtxsid")
	private String dtxsid;
	
	@Column(name = "chemical_name")
	private String chemicalName;
	
	@Column(name = "casrn")
	private String casrn;
	
	@Column(name = "last_significant_revision")
	private Date lastSignificantRevision;
	
	@Column(name = "literature_screening_review")
	private String literatureScreeningReview;
	
	@Column(name = "critical_effect_systems")
	private String criticalEffectsSystems;
	
	@Column(name = "rfd_chronic")
	private String rfdChronic;
	
	@Column(name = "rfd_subchronic")
	private String rfdSubchronic;
	
	@Column(name = "rfc_chronic")
	private String rfcChronic;
	
	@Column(name = "rfc_subchronic")
	private String rfcSubchronic;
	
	@Column(name = "tumor_site")
	private String tumorSite;
	
	@Column(name = "iris_url")
	private String irisUrl;
}

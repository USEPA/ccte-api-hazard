package gov.epa.ccte.api.hazard.domain;


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
@Schema(description = "EPA Health Assessment Workspace Collaborative (HAWC). EPA HAWC is an interactive, expert-driven, content management system for EPA health and environmental risk assessment programs that is intended to promote transparency, data usability, and understanding of the data and decisions supporting health and environmental assessments. Specifically, EPA HAWC is an application that allows the data and decisions supporting an assessment to be evaluated and managed in modules (e.g., study evaluation, summary study data) that can then be publicly accessed online.")
@Table(name = "mv_hawc_ccd_map", schema = "hawc")
public class Hawc {

	@Id
   	@NotNull
	@Column(name = "assessment_id")
	private Integer assessmentId;
	
	@Column(name = "dtxsid")
	private String dtxsid;
	
	@Column(name = "ccd_url")
	private String ccdUrl;
	
	@Column(name = "hawc_link")
	private String hawcLink;
}

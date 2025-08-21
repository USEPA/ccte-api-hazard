package gov.epa.ccte.api.hazard.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "A carcinogen is a substance that may cause cancer and pose a health hazard. Carcinogens are subject to specific controls and regulations. National and international health agencies evaluate new and existing chemicals to determine if they are likely to be carcinogens through a process called cancer hazard identification. This process combines human and animal data with supporting evidence to characterize the weight-of-evidence (WOE) regarding the agent's potential as a human carcinogen. The general categories recognized by the guidelines are carcinogenic to humans, likely to be carcinogenic to humans, and suggestive evidence of carcinogenic potential. This endpoint provides summary-level cancer data associated to a chemical.")
@Table(name = "mv_cancer_summary", schema = "toxval")
public class CancerSummary {
	
	@Id
    @NotNull
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @Column(name = "dtxsid")
    @Schema(description = "Distributed Structure-Searchable Toxicity (DSSTox) id")
    private String dtxsid;
	
    @Size(max = 255)
    @Column(name = "source")    
    @Schema(description = "Originator of source data")
    private String source;
    
    @Size(max = 255)
    @Column(name = "exposure_route")
    @Schema(description = "Path by which test substance was administered to animal. Options include oral, dermal, inhalation, injection, or other")
    private String exposureRoute;
    
    @Size(max = 255)
    @Column(name = "cancer_call")
    @Schema(description = "Annotation indicating level of carcinogenicity")
    private String cancerCall;
    
    @Size(max = 255)
    @Column(name = "url")    
    @Schema(description = "URL for source data")
    private String url;
        
}

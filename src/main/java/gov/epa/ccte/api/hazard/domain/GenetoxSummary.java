package gov.epa.ccte.api.hazard.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Description: Genotoxicity testing, also known as short-term assays, is used to evaluate the potential adverse effects of chemicals on human health. These tests can help identify carcinogens, as about 80% of known human carcinogens are genotoxic. The results of genotoxicity tests are important because of the irreversible nature and severity of the health effects that may result from genotoxic events. There are many different techniques that can be used for genotoxicity testing, notably including Ames test (comparative genetic analysis using multiple bacterial strains of Salmonella typhimurium), Comet assay (measurement DNA strand breaks), micronucleus test (cytogenetic assay that uses fluorescence in situ hybridization and chromosome painting) and chromosomal aberration test (cytogenetic assay that examines in vivo tissues to reflect how chemicals are absorbed, excreted, distributed, and metabolized). This endpoint provides summary-level genotoxicity data associated to a chemical.")
@Entity
@Table(name = "mv_genetox_summary", schema = "toxval")
public class GenetoxSummary {
	
    @Id
    @NotNull
    @Column(name = "id", nullable = false)
    private Integer id;
    
    @Size(max = 255)
    @Column(name = "ames")
    @Schema(description = "Average result in AMES test")
    private String ames;
    
    @Size(max= 65)
    @Column(name = "clowder_doc_id")
    private String clowderDocId;
    
    @Size(max = 255)
    @Column(name = "dtxsid")
    @Schema(description = "Distributed Structure-Searchable Toxicity (DSSTox) id")
    private String dtxsid;
    
    @Size(max = 255)
    @Column(name = "genetox_call")
    private String genetoxCall;
    
    @Column(name = "genetox_summary_id")
    private Integer genetoxSummaryId;
    
    @Size(max = 255)
    @Column(name = "micronucleus")
    @Schema(description = "Average result in Micronucleus test")
    private String micronucleus;

    @Column(name = "reports_neg")
    @Schema(description = "Count of genetox records reporting negative results")
    private Integer reportsNegative;

    @Column(name = "reports_other")
    @Schema(description = "Count of genetox records reporting ‘other’ in results")
    private Integer reportsOther;
    
    @Column(name = "reports_pos")
    @Schema(description = "Count of genetox records reporting positive results")
    private Integer reportsPositive;

}
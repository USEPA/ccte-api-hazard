package gov.epa.ccte.api.hazard.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "mv_pprtv_chemicals", schema = "pprtv")
public class Pprtv {
    @Id
    @NotNull
    @Column(name = "id")
    private Integer id;
    
    @Size(max = 45)
    @Column(name = "dtxsid")
    @Schema(description = "Distributed Structure-Searchable Toxicity (DSSTox) id")
    private String dtxsid;
    
    @Column(name = "pprtv_substance_id")
    private Integer pprtvSubstanceId;
    
    @Column(name = "chemical_name")
    private String name;
    
    @Column(name = "casrn")
    private String casrn;
    
    @Column(name = "last_revision")
    private Integer lastReviosn;
    
    @Column(name = "pprtv_assessment")
    private String pprtvAssessment;
    
    @Column(name = "iris_link")
    private String irisLink;
    
    @Column(name = "rfc_value")
    private String rfcValue;
    
    @Column(name = "rfd_value")
    private String rfdValue;
    
    @Column(name = "woe")
    private String woe;
}

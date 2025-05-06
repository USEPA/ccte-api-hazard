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
@Schema(description = "The information here describes toxicokinetics in humans. Values are 1) measured either in vitro or in vivo, 2) predicted from chemical properties using in silico tools, or 3) computed with mathematical models simulating toxicokinetics. Note: intrinsic hepatic clearance and fraction unbound in plasma values can have measured values listed, but these may be assumed from measured data using a surrogate species. The Data Source Species column identifies the measured data species source. We use these assumed measured values in calculations to estimate other outcomes, such as volume of distribution, PK half life, and steady-state plasma concentration. The in vitro measured values reflect the value curated for the open source R package “httk”. The computed values are generated with httk using the reported in vitro values. \"https://CRAN.R-project.org/package=httk\". The in vivo measured values are estimated from toxicokinetic concentration vs. time contained in the CvTdb (https://doi.org/10.1038/s41597-020-0455-1). In vivo estimates are calculated using R package “invivoPKfit” (https://github.com/USEPA/CompTox-ExpoCast-invivoPKfit)")
@Table(name = "mv_ivive", schema = "adme")
public class Adme {
    @Id
    @NotNull
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "dtxsid")
    @Schema(description = "Distributed Structure-Searchable Toxicity (DSSTox) id")
    private String dtxsid;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "measured")
    private String measured;
    
    @Column(name = "predicted")
    private String predicted;
    
    @Size(max = 30)
    @Column(name = "unit")
    private String unit;
    
    @Column(name = "model")
    private String model;
    
    @Column(name = "reference")
    private String reference;
    
    @Column(name = "percentile")
    private String percentile;
    
    @Column(name = "species")
    private String species;
    
    @Column(name = "data_source_species")
    private String dataSourceSpecies;
    
    }

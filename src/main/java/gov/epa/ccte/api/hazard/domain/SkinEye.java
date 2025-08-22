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
@Schema(description = "Skin and eye irritation tests are used to assess the safety of products and chemicals that may come into contact with the skin or eyes. These tests are important for labeling and registering chemicals and agrochemicals, and for developing drugs. Types of skin and eye irritation tests include Draize test, EpiOcular Eye Irritation Test (EIT), Red blood cells test, or Patch test")
@Table(name = "mv_skin_eye", schema = "toxval")
public class SkinEye {
	
    @Id
    @NotNull
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 45)
    @Column(name = "authority")
    private String authority;
    
    @Size(max = 255)
    @Column(name = "classification")    
    @Schema(description = "Classification into hazard categories based on the severity of the skin or eye irritation")
    private String classification;

    @Size(max = 255)
    @Column(name = "dtxsid")    
    @Schema(description = "Distributed Structure-Searchable Toxicity (DSSTox) id ")
    private String dtxsid;

    @Size(max = 255)
    @Column(name = "endpoint")
    @Schema(description = "Skin sensitization or eye irritation")
    private String endpoint;
    
    @Column(name = "glp")
    private String glp;

    @Column(name = "guideline")   
    @Schema(description = "Number associated with the particular guideline that a test adheres to or most closely adheres to")
    private String guideline;
    
    @Size(max = 1024)
    @Column(name = "record_url")
    private String recordUrl;

    @Size(max = 45)
    @Column(name = "reliability")    
    @Schema(description = "Ranking of reliability of results")
    private String reliability;

    @Size(max = 1024)
    @Column(name = "result_text")
    @Schema(description = "Annotation describing results")
    private String resultText;

    @Size(max = 45)
    @Column(name = "score")
    @Schema(description = "Hazard scoring based on classification")
    private String score;
    
    @Size(max = 255)
    @Column(name = "skin_eye_hash")
    private String skinEyeHash;
    
    @Column(name = "skin_eye_id")
    private Integer skinEyeId;
    
    @Size(max = 255)
    @Column(name = "skin_eye_uuid")
    private String skinEyeUuid;

    @Size(max = 45)
    @Column(name = "source")
    @Schema(description = "Originator of source data")
    private String source;

    @Size(max = 255)
    @Column(name = "species")
    @Schema(description = "Species of the animal test subject used in a study")
    private String species;

    @Size(max = 255)
    @Column(name = "strain")
    @Schema(description = "Intraspecific description of group of animals used in a study; generally, a stock of animals that share a uniform morphological or physiological character, or group that is genetically uniform")
    private String strain;

    @Size(max = 255)
    @Column(name = "study_type")
    @Schema(description = "Classification to describe testing that was conducted")
    private String studyType;

    @Column(name = "year")
    @Schema(description = "Year in which the study was reported as finished")
    private Integer year;
}

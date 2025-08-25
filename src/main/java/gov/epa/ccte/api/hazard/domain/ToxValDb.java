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

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "mv_toxvaldb", schema = "toxval")
@Schema(description = "The Toxicity Values Database (ToxValDB) is a compilation of toxicity information from world-wide sites, databases, and sources, which can save the user time by providing information in one location. The data are largely limited to summary values from individual studies or chemical-level assessments, and is focused on quantitative values such as LOAELs, NOAELs, BMDs, LD50s and RfDs. The user must apply judgment in use of the information and should consult the original scientific paper or data source, if possible, to ensure an understanding of the context of the data. The ToxValDB SQL download is available at https://www.epa.gov/comptox-tools/downloadable-computational-toxicology-data#AT .")
public class ToxValDb {
	
    @Id
    @NotNull
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 45)
    @Column(name = "dtxsid", length = 45)
    @Schema(description = "Distributed Structure-Searchable Toxicity (DSSTox) id")
    private String dtxsid;
    
    @Column(name = "casrn")
    private String casrn;
    
    @Column(name = "name")
    private String name;
    
    @Size(max = 255)
    @Column(name = " source")
    @Schema(description = "Originator of source data")
    private String source;
    
    @Size(max = 255)
    @Column(name = "sub_source")
    @Schema(description = "Additional source reference")
    private String subsource;

    @Size(max = 255)
    @Column(name = "toxval_type")
    @Schema(description = "Categorization of records based on POD or effect level. Categories include: Point of Departure (The quantitative result of an in vivo repeat dose study, such as a LOAEL, NOAEL or BMDL), Lethality Effect Level (The quantitative result of an in vivo acute study, such as a LD50 or LC50), Toxicity Value (A risk assessment summary value for a chemical, such as an RFD or cancer slope factor), Screening Level (An environmentally safe concentration, primarily EPA Regional Screening levels), Exposure Limit (Exposure levels of concern in air, water, or other media), Effect Time (A time when an effect is seen for a fixed concentration. These values all come from the ECOTOX Knowledgebase), Effect Level (A value similar to a Point of Departure, but not linked to a critical effect), Effect Dose (Similar to a Lethality Effect Level but for non-lethal ecotoxicology endpoints in acute studies), Inhibition Concentration (Similar to a Lethality Effect Level but for non-lethal ecotoxicology endpoints, focused on inhibition of growth), Other (Other uncharacterized classes of records)")
    private String toxvalType;

    @Column(name = "toxval_type_defn")
    private String toxvalTypeDefinition;
    
    @Size(max = 255)
    @Column(name = "toxval_subtype")
    @Schema(description = "Effect subtype or value subtype")
    private String toxvalSubtype;

    @Size(max = 255)
    @Column(name = "toxval_type_supercategory")
    private String toxvalTypeSuperCategory;
    
    @Size(max = 255)
    @Column(name = "qualifier")
    @Schema(description = "Operators (<, <=, >, >=, =) to contextualize level of certainty in estimates")
    private String qualifier;
    
    @Column(name = "toxval_numeric")
    @Schema(description = "Concentration of a test chemical, typically reported in ppm within the exposure matrix (e.g., feed or water), or the an adjusted amount of the chemical administered in mg/kg of body weight/day (mg/kg/day)")
    private Double toxvalNumeric;

    @Size(max = 255)
    @Column(name = "toxval_units")
    @Schema(description = "Unit associated with the concentration or adjusted dose of a chemical")
    private String toxvalUnits;

    @Size(max = 255)
    @Column(name = "risk_assessment_class")
    @Schema(description = "Designation of cancer or non-cancer for toxicity values or media associated with exposure guideline values")
    private String riskAssessmentClass;
    
    @Size(max = 255)
    @Column(name = "human_eco")
    private String humanEco;
    
    @Size(max = 255)
    @Column(name = "study_type")
    @Schema(description = "Classification to describe toxicity testing that was conducted")
    private String studyType;

    @Size(max = 255)
    @Column(name = "study_duration_class")
    @Schema(description = "Categorical description of exposure duration ")
    private String studyDurationClass;

    @Column(name = "study_duration_value")
    @Schema(description = "Amount of time a group is dosed. This varies within studies depending on the dose period of a particular treatment group")
    private Double studyDurationValue;

    @Size(max = 255)
    @Column(name = "study_duration_units")
    @Schema(description = "Unit of time associated with the dose duration. Typically in days or months")
    private String studyDurationUnits;

    @Size(max = 255)
    @Column(name = "species_common")
    @Schema(description = "Species of the animal test subject used in a study")
    private String speciesCommon;
    
    @Size(max = 255)
    @Column(name = "strain")
    @Schema(description = "Intraspecific description of group of animals used in a study; generally, a stock of animals that share a uniform morphological or physiological character, or group that is genetically uniform")
    private String strain;
    
    @Size(max = 255)
    @Column(name = "latin_name")
    private String latinName;
    
    @Size(max = 255)
    @Column(name = "species_supercategory")
    @Schema(description = "Categorization of effect or value type into one of five categories, including dose response summary value, mortality response summary value, toxicity value, media exposure guidelines, or acute exposure guidelines")
    private String speciesSupercategory;
    
    @Size(max = 255)
    @Column(name = "sex")
    @Schema(description = "Gender of a test animal. The gender of fetal groups is denoted as MF for both males and females")
    private String sex;
    
    @Size(max = 255)
    @Column(name = "generation")
    @Schema(description = "Generation a test animal belongs to")
    private String generation;
    
    @Size(max = 255)
    @Column(name = "lifestage")
    @Schema(description = "Stage of life that a measurement was taken")
    private String lifestage;
    
    @Size(max = 255)
    @Column(name = "exposure_route")
    @Schema(description = "Path by which test substance was administered to animal. Options include oral, dermal, inhalation, injection, or other")
    private String exposureRoute;

    @Size(max = 255)
    @Column(name = "exposure_method")
    @Schema(description = "Specific path by which the test substance was administered via the administration route. Examples include capsule, diet, gavage, or topical")
    private String exposureMethod;

    @Size(max = 255)
    @Column(name = "exposure_form")
    @Schema(description = "The vehicle used in administration of the chemical")
    private String exposureForm;
    
    @Size(max = 255)
    @Column(name = "media")
    private String media;

    @Column(name = "toxicological_effect")
    @Schema(description = "Treatment-associated effect ")
    private String toxicologicalEffect;
    
    @Column(name = "experimental_record")
    private String experimentalRecord;
    
    @Size(max = 255)
    @Column(name = "study_group")
    private String studyGroup;
    
    @Column(name = "long_ref")
    private String longRef;
    
    @Column(name = "doi")
    private String doi;
    
    @Column(name = "title")
    private String title;
    
    @Size(max = 255)
    @Column(name = "author")
    private String author;
    
    @Size(max = 255)
    @Column(name = "year")
    private String year;
    
    @Size(max = 255)
    @Column(name = "guideline")
    private String guideline;
    
    @Size(max = 255)
    @Column(name = "quality")
    private String quality;
    
    @Column(name = "qc_category")
    private String qcCategory;
    
    @Size(max = 45)
    @Column(name = "source_hash")
    private String sourceHash;
    
    @Column(name = "external_source_id")
    private String externalSourceId;
    
    @Column(name = "external_source_id_desc")
    private String externalSourceIdDesc;

    @Size(max = 255)
    @Column(name = "source_url")
    @Schema(description = "URL for source data")
    private String sourceUrl;

    @Size(max = 255)
    @Column(name = "subsource_url")
    @Schema(description = "Additional URL reference for source data")
    private String subsourceUrl;

    @Size(max = 255)
    @Column(name = "stored_source_record")
    private String storedSourceRecord;
    
    @Size(max = 255)
    @Column(name = "toxval_type_original")
    @Schema(description = "Categorization of records based on POD or effect level. Categories include: Point of Departure (The quantitative result of an in vivo repeat dose study, such as a LOAEL, NOAEL or BMDL), Lethality Effect Level (The quantitative result of an in vivo acute study, such as a LD50 or LC50), Toxicity Value (A risk assessment summary value for a chemical, such as an RFD or cancer slope factor), Screening Level (An environmentally safe concentration, primarily EPA Regional Screening levels), Exposure Limit (Exposure levels of concern in air, water, or other media), Effect Time (A time when an effect is seen for a fixed concentration. These values all come from the ECOTOX Knowledgebase), Effect Level (A value similar to a Point of Departure, but not linked to a critical effect), Effect Dose (Similar to a Lethality Effect Level but for non-lethal ecotoxicology endpoints in acute studies), Inhibition Concentration (Similar to a Lethality Effect Level but for non-lethal ecotoxicology endpoints, focused on inhibition of growth), Other (Other uncharacterized classes of records) (original)")
    private String toxvalTypeOriginal;
    
    @Size(max = 255)
    @Column(name = "toxval_subtype_original")
    @Schema(description = "Effect subtype or value subtype (original)")
    private String toxvalSubtypeOriginal;
    
    @Size(max = 255)
    @Column(name = "toxval_numeric_original")
    @Schema(description = "Concentration of a test chemical, typically reported in ppm within the exposure matrix (e.g., feed or water), or the an adjusted amount of the chemical administered in mg/kg of body weight/day (mg/kg/day) (original)")
    private Double toxvalNumericOriginal;
    
    @Size(max = 255)
    @Column(name = "toxval_units_original")
    @Schema(description = "Unit associated with the concentration or adjusted dose of a chemical (original)")
    private String toxvalUnitsOriginal;
    
    @Size(max = 255)
    @Column(name = "study_type_original")
    @Schema(description = "Classification to describe toxicity testing that was conducted (original)")
    private String studyTypeOriginal;
    
    @Size(max = 255)
    @Column(name = "study_duration_class_original")
    @Schema(description = "Categorical description of exposure duration (original)")
    private String studyDurationClassOriginal;
    
    @Size(max = 255)
    @Column(name = "study_duration_value_original")
    @Schema(description = "Amount of time a group is dosed. This varies within studies depending on the dose period of a particular treatment group (original)")
    private String studyDurationValueOriginal;
    
    @Size(max = 255)
    @Column(name = "study_duration_units_original")
    @Schema(description = "Unit of time associated with the dose duration. Typically in days or months (original)")
    private String studyDurationUnitsOriginal;
    
    @Size(max = 255)
    @Column(name = "species_original")
    @Schema(description = "Species of the animal test subject used in a study (original)")
    private String speciesOriginal;
    
    @Size(max = 255)
    @Column(name = "strain_original")
    @Schema(description = "Intraspecific description of group of animals used in a study; generally, a stock of animals that share a uniform morphological or physiological character, or group that is genetically uniform (original)")
    private String strainOriginal;
    
    @Size(max = 255)
    @Column(name = "sex_original")
    @Schema(description = "Gender of a test animal. The gender of fetal groups is denoted as MF for both males and females (original)")
    private String sexOriginal;
    
    @Size(max = 255)
    @Column(name = "generation_original")
    @Schema(description = "Generation a test animal belongs to (original)")
    private String generationOriginal;

    @Size(max = 255)
    @Column(name = "lifestage_original")
    @Schema(description = "Stage of life that a measurement was taken (original)")
    private String lifestageOriginal;
    
    @Size(max = 255)
    @Column(name = "exposure_route_original")
    @Schema(description = "Path by which test substance was administered to animal. Options include oral, dermal, inhalation, injection, or other (original)")
    private String exposureRouteOriginal;

    @Size(max = 255)
    @Column(name = "exposure_method_original")
    @Schema(description = "Specific path by which the test substance was administered via the administration route. Examples include capsule, diet, gavage, or topical (original")
    private String exposureMethodOriginal;

    @Size(max = 255)
    @Column(name = "exposure_form_original")
    @Schema(description = "The vehicle used in administration of the chemical (original)")
    private String exposureFormOriginal;
    @Size(max = 255)
    @Column(name = "media_original")
    private String mediaOriginal;

    @Column(name = "toxicological_effect_original")
    @Schema(description = "Treatment-associated effect (original)")
    private String toxicologicalEffectOriginal;
    
    @Size(max = 255)
    @Column(name = "original_year")
    @Schema(description = "Year in which the study was reported as finished")
    private String originalYear;

}
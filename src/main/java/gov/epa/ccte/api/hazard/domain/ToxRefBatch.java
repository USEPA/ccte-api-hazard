package gov.epa.ccte.api.hazard.domain;

import org.hibernate.annotations.Immutable;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Export of all extracted dose-treatment group-effect information as well as doses not eliciting effects from ToxRefDB for the dtxsids listed in the input. Data from ToxRefDB is also summarized in ToxValDB, including inferred NEL and NOAEL effect levels based on the reported LEL and LOAEL, respectively.")
@Entity
@Immutable
@Table(name = "toxref_batch_search", schema = "toxref")
public class ToxRefBatch {
    @Id
    @Column(name = "study_id")
    @Schema(description = "A unique numeric identifier for each study in the database.")
    private Integer studyId;

    @Size(max = 45)
    @Column(name = "dsstox_substance_id", length = 45)
    @Schema(description = "Unique identifier from DSSTox")
    private String dtxsid;

    @Size(max = 255)
    @Column(name = "casrn")
    @Schema(description = "Curated casrn from DSSTOX chemical curation")
    private String casrn;

    @Size(max = 255)
    @Column(name = "name")
    @Schema(description = "Curated chemical name from DSSTOX chemical curation")
    private String name;

    @Size(max = 255)
    @Column(name = "study_source")
    @Schema(description = "Organization that provided or authored the document for curation.")
    private String studySource;

    @Size(max = 255)
    @Column(name = "study_source_id")
    @Schema(description = "Unique document identifier provided by the study source.")
    private String studySourceId;

    @Size(max = 1024)
    @Column(name = "citation", length = 1024)
    @Schema(description = "Citation in source document")
    private String citation;

    @Column(name = "study_year")
    private String studyYear;

    @Size(max = 255)
    @Column(name = "study_type")
    @Schema(description = "Classification to describe animal toxicity testing that was conducted. Based on either dose period, subjects involved, or measured effects of interest, study types include: ACU (acute): Dose period typically a day or less. Excludes developmental and neurological studies.; SAC (subacute): Dose period is typically 21-28 days. Excludes developmental and neurological studies.; SUB (subchronic): Dose period is typically 13 weeks, but may be as long as 6 months. Excludes developmental and neurological studies.; CHR (chronic): Dose period is typically 12, 18, or 24 months (generally any dosing lasting a year or longer). Excludes developmental and neurological studies.; DEV (developmental): Gestational (in utero) dose period. Sacrificed prior to delivery.; MGR (multigenerational reproductive): Dose period begins in adolescent F0 males and females and continues until terminal generation. At least some of the litters deliver their pups, some may be sacrificed prior to delivery.; NEU (neurological): Study contains functional observation battery or other battery of behavioral testing that occurs during or after dosing. Pathology has specific interest in the brain (i.e. regions, morphology, biochemistry, et cetera). excludes developmental studies; DNT (developmental neurotoxicity): dose period occurs anytime during development (i.e. in utero, lactational, adolescent [after weaning, before adulthood]). Study contains functional observation battery or other battery of behavioral testing that occurs during or after dosing, typically during adulthood. Pathology has specific interest in the brain (i.e. regions, morphology, biochemistry, etc.)")
    private String studyType;

    @Size(max = 255)
    @Column(name = "study_type_guideline")
    @Schema(description = "Description that combines the study_type and guideline name for a study.")
    private String studyTypeGuideline;

    @Size(max = 255)
    @Column(name = "species")
    @Schema(description = "Species of the animal test subject used in a study.")
    private String species;

    @Size(max = 255)
    @Column(name = "strain_group")
    @Schema(description = "Descriptive category for a group of test animals that is more general than the strain. ")
    private String strainGroup;

    @Size(max = 255)
    @Column(name = "strain")
    @Schema(description = "Intraspecific description of group of animals used in a study; generally, a stock of animals that share a uniform morphological or physiological character, or group that is genetically uniform.")
    private String strain;

    @Size(max = 255)
    @Column(name = "admin_route")
    @Schema(description = "Path by which test substance was administered to animal. Options include oral, dermal, inhalation, injection, or other.")
    private String adminRoute;

    @Size(max = 255)
    @Column(name = "admin_method")
    @Schema(description = "Specific path by which the test substance was administered via the administration route. Examples include capsule, diet, gavage, or topical.")
    private String adminMethod;

    @Column(name = "dose_duration")
    @Schema(description = "Amount of time a group is dosed. This varies within studies depending on the dose period of a particular treatment group.")
    private String doseDuration;

    @Size(max = 16)
    @Column(name = "dose_duration_unit", length = 16)
    @Schema(description = "Unit of time associated with the dose duration. Typically in days or months.")
    private String doseDurationUnit;

    @Column(name = "dose_start")
    @Schema(description = "Time during an animal's life that the administration of a test substance began.")
    private Integer doseStart;

    @Size(max = 255)
    @Column(name = "dose_start_unit")
    @Schema(description = "Unit of time associated with the start of the dose (dose_start).")
    private String doseStartUnit;

    @Column(name = "dose_end")
    @Schema(description = "Time during an animal's life that the administration of a test substance stopped.")
    private Integer doseEnd;

    @Size(max = 255)
    @Column(name = "dose_end_unit")
    @Schema(description = "Unit of time associated with the end of the dose (dose_end).")
    private String doseEndUnit;

    @Size(max = 32)
    @Column(name = "dose_period", length = 32)
    @Schema(description = "Period within a group's lifetime that the animals were dosed and the sample for an endpoint's data was taken (when the animals were sacrificed).")
    private String dosePeriod;

    @Column(name = "dose_level")
    @Schema(description = "Numeric rank indicating the level of dose administered to test animals, with lower dose levels indicating lower concentrations of a chemical (e.g., 0 = vehicle, 1 = lowest dose, etc.). The dose level for some studies may be staggered since concentrations may vary by sex (e.g, male treatment group: 0 = vehicle, 1 = lowest dose, 3 = second lowest dose, etc.).")
    private Integer doseLevel;

    @Column(name = "conc")
    @Schema(description = "Concentration of a test chemical, typically reported in ppm within the exposure matrix (e.g., feed or water).")
    private String conc;

    @Size(max = 255)
    @Column(name = "conc_unit")
    @Schema(description = "Unit associated with a concentration of a test chemical, typically reported as ppm.")
    private String concUnit;

    @Size(max = 255)
    @Column(name = "vehicle")
    @Schema(description = "The media used in administration of the chemical.")
    private String vehicle;

    @Size(max = 1024)
    @Column(name = "dose_comment", length = 1024)
    @Schema(description = "NULL if no additional comment needed. Field can be used to explain any differences in dosing over the dosing interval and/or clarifying comments on how the dose was administered.")
    private String doseComment;

    @Column(name = "dose_adjusted")
    @Schema(description = "The amount of the chemical administered in mg/kg of body weight/day (mg/kg/day). This value is typically different between male and female groups receiving the same dose concentration (conc) due to differences in bodyweight. If dose_adjusted values were not provided in a study, then they were calculated using species scaling factors (FAO/WHO, 2000).")
    private String doseAdjusted;

    @Size(max = 32)
    @Column(name = "dose_adjusted_unit", length = 32)
    @Schema(description = "Unit associated with the adjusted dose of a chemical, typically reported in mg/kg/day.")
    private String doseAdjustedUnit;

    @Size(max = 8)
    @Column(name = "sex", length = 8)
    @Schema(description = "Gender of a test animal. The gender of fetal groups is denoted as MF for both males and females.")
    private String sex;

    @Size(max = 16)
    @Column(name = "generation", length = 16)
    @Schema(description = "Generation a test animal belongs to. The F0 generation is the first generation mating group for MGR studies and is the default for non-reproductive studies (CHR, SUB, SAC). F1 is the second generation mating group, selected from either F1a or F1b litters. F2 is the third generation mating group, selected from either F2a or F2b litters. F1a and F1b are the first and second litter groups produced by F0 matings, F2a and F2b are the first and second litter groups produced by F1 matings, and F3a and F3b are the first and second litter groups produced by F2 matings. The fetal generation is the group produced by F0 matings in DEV studies, and are typically removed from a female via cesarean section in DEV studies.")
    private String generation;

    @Size(max = 32)
    @Column(name = "life_stage", length = 32)
    @Schema(description = "Stage of life that a measurement was taken. CHR, SUB, and SAC studies typically only have adult for life_stage, whereas DEV and MGR studies will always be characterized by multiple life stages. The different life stages in the database include: fetal, juvenile, adult, adult-pregnancy and pregnancy.")
    private String lifeStage;

    @Size(max = 255)
    @Column(name = "num_animals")
    @Schema(description = "Number of animals per treatment group")
    private String numAnimals;

    @Size(max = 1024)
    @Column(name = "tg_comment", length = 1024)
    @Schema(description = "NULL if no additional comment needed; contains information that the extractor/curator found helpful in describing issues related to a treatment-group (e.g. animals dosed via capsule so concentration not reported, added recovery groups, etc.). ")
    private String tgComment;

    @Size(max = 255)
    @Column(name = "endpoint_category")
    @Schema(description = "The broadest descriptive term for an endpoint. Possible endpoint categories include: systemic,  developmental, reproductive, and cholinesterase.")
    private String endpointCategory;

    @Size(max = 255)
    @Column(name = "endpoint_type")
    @Schema(description = "The subcategory for endpoint_category, which is more descriptive for a particular endpoint (e.g. pathology gross, clinical chemistry, reproductive performance, etc.)")
    private String endpointType;

    @Size(max = 255)
    @Column(name = "endpoint_target")
    @Schema(description = "More specific description than endpoint_type, often indicating where or how the sample was collected to supply data for a particular endpoint. Target may describe an organ, tissue, metabolite or protein measured.")
    private String endpointTarget;

    @Size(max = 255)
    @Column(name = "effect_desc")
    @Schema(description = "More specific description for an effect than endpoint_category, usually detailing a specific condition associated with an endpoint_target (e.g. dysplasia, atrophy, necrosis, etc.).")
    private String effectDesc;

    @Size(max = 255)
    @Column(name = "effect_desc_free")
    @Schema(description = "Brief verbatim text from study file that was entered if the effect description differed from predetermined endpoint terminology.")
    private String effectDescFree;

    @Column(name = "cancer_related")
    @Schema(description = "Indicates if effect is considered cancer related (1) or not.")
    private Boolean cancerRelated;

    @Size(max = 64)
    @Column(name = "target_site", length = 64)
    @Schema(description = "A more specific description than effect_target. Can describe a specific tissue within an organ, type of cell, etc.")
    private String targetSite;

    @Column(name = "direction")
    @Schema(description = "Description of the net change across all doses that indicates whether the numerical data increased, decreased, or stayed the same. Also can be used to describe effects that did not have numerical data, but were still described in the study source.")
    private Integer direction;

    @Size(max = 1024)
    @Column(name = "effect_comment", length = 1024)
    @Schema(description = "NULL if no additional comment needed; contains information that the extractor/curator found helpful in describing issues related to a treatment-group-effect (e.g. units not reported, effect only reported for certain treatment groups, etc.).")
    private String effectComment;

    @Column(name = "treatment_related")
    @Schema(description = "Boolean description for an effect by dose treatment group. â€œTRUEâ€\u009D indicates there was a statistically significant difference from the control group for the effect; â€œFALSEâ€\u009D indicates there was no difference from control group. The highest dose level at which no significant observable adverse effects were observed corresponds to the no effect level (NEL). The lowest effect level (LEL) can be inferred by treatment related effects.")
    private String treatmentRelated;

    @Column(name = "critical_effect")
    @Schema(description = "Boolean description for an effect by dose treatment group. â€œTRUEâ€\u009D corresponds to a toxic or adverse effect denoted in the study summary or via expert judgement using a weight-of-evidence approach. â€œFALSEâ€\u009D indicates that although an effect is produced at this level, it is not considered adverse, nor immediate precursors to specific adverse effects. If there are several critical effects, the no observed adverse effect level (NOAEL) is determined from the highest dose level without critical effects. The lowest dose level at which the critical effect was observed in a study is the lowest observed adverse effect level (LOAEL.)")
    private Boolean criticalEffect;

    @Size(max = 8)
    @Column(name = "sample_size", length = 8)
    @Schema(description = "Number of animals used for an examination for a particular effect.")
    private String sampleSize;

    @Column(name = "effect_val")
    @Schema(description = "Numeric value of a measured effect, can be continuous or dichotomous (incidence) data.")
    private String effectVal;

    @Size(max = 128)
    @Column(name = "effect_val_unit", length = 128)
    @Schema(description = "Unit associated with the effect value.")
    private String effectValUnit;

    @Column(name = "effect_var")
    @Schema(description = "Measurement of the variance for a set of data associated with a measured effect, generally reported as the standard deviation (SD) or standard error (SE).")
    private String effectVar;

    @Size(max = 32)
    @Column(name = "effect_var_type", length = 32)
    @Schema(description = "Name of the variance metric used to determine the effect variance, typically the standard deviation (SD) or standard error (SE). Other effect_var types include: interquartile range, 95% confidence limit, and none.")
    private String effectVarType;

    @Column(name = "time")
    @Schema(description = "Numeric value associated with the duration of the exposure at which a particular effect was measured or observed, typically reported in hours, days, weeks, or months.")
    private String time;

    @Size(max = 64)
    @Column(name = "time_unit", length = 64)
    @Schema(description = " Unit associated with the duration of the exposure at which a particular effect was measured or observed, typically reported in hours, days, weeks, or months.")
    private String timeUnit;

    @Column(name = "no_quant_data_reported")
    @Schema(description = "Indicates if qualitative (1) or quantitative (0) effect data was reported.")
    private Boolean noQuantDataReported;

    @Column(name = "tbs_key")
    @Schema(description = "Date view of ToxRefDB data was created for API")
    private Long tbsKey;

}

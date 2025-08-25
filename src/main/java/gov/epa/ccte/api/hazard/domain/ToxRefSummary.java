package gov.epa.ccte.api.hazard.domain;

import java.time.LocalDate;

import org.hibernate.annotations.Immutable;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Immutable
@Table(name = "mv_summary", schema = "toxref")
public class ToxRefSummary {

	@Id
    @NotNull
    @Column(name = "id", nullable = false)
    private Long id;
	
	@Column(name = "study_id")
    @Schema(description = "A unique numeric identifier for each study in the database.")
	private Integer studyId;

	@Size(max = 45)
	@Column(name = "dtxsid", length = 45)
	@Schema(description = "Unique identifier from DSSTox")
	private String dtxsid;

	@Size(max = 255)
	@Column(name = "casrn")
	@Schema(description = "Curated casrn from DSSTOX chemical curation")
	private String casrn;

	@Size(max = 255)
	@Column(name = "chemical_name")
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
	@Column(name = "study_citation", length = 1024)
	@Schema(description = "Citation in source document")
	private String citation;

	@Column(name = "study_year")
	private Integer studyYear;

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

	@Column(name = "export_date")
	@Schema(description = "Date view of ToxRefDB data was created for API")
	private LocalDate exportDate;

	@Column(name = "data_version", length = Integer.MAX_VALUE)
	@Schema(description = "Version of ToxRefDB data")
	private String version;
}

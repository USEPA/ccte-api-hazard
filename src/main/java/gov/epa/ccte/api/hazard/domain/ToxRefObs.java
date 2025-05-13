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
import org.hibernate.annotations.Immutable;

import java.time.LocalDate;

@Getter
@Setter
@Schema(description = "Export of all observations and endpoint observation status according to a relevant guideline profile for the chemicals, studies, or study types listed in the input. Given ToxRefDB curations are guideline studies with specific testing requirements, guideline profiles are used to populate a list of observations that should be reported and tested within the study, i.e. endpoint observation status. Endpoint Observation status enables automated distinction of true negatives (i.e. tested with no effect observed) and better understanding of false negative (i.e. missing) effects.")
@Entity
@Immutable
@Table(name = "mv_obs", schema = "toxref")
public class ToxRefObs {
    @Id
    @NotNull
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "study_id")
    @Schema(description = "Autoincremented unique numeric identifier for each study in ToxRefDB")
    private Integer studyId;

    @Size(max = 45)
    @Column(name = "dtxsid", length = 45)
    @Schema(description = "Identifier assigned through DSSTox chemical curation")
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
    @Column(name = "study_type")
    @Schema(description = "Classification to describe animal toxicity testing that was conducted. Based on either dose period, subjects involved, or measured effects of interest, study types include: ACU (acute): Dose period typically a day or less. Excludes developmental and neurological studies.; SAC (subacute): Dose period is typically 21-28 days. Excludes developmental and neurological studies.; SUB (subchronic): Dose period is typically 13 weeks, but may be as long as 6 months. Excludes developmental and neurological studies.; CHR (chronic): Dose period is typically 12, 18, or 24 months (generally any dosing lasting a year or longer). Excludes developmental and neurological studies.; DEV (developmental): Gestational (in utero) dose period. Sacrificed prior to delivery.; MGR (multigenerational reproductive): Dose period begins in adolescent F0 males and females and continues until terminal generation. At least some of the litters deliver their pups, some may be sacrificed prior to delivery.; NEU (neurological): Study contains functional observation battery or other battery of behavioral testing that occurs during or after dosing. Pathology has specific interest in the brain (i.e. regions, morphology, biochemistry, et cetera). excludes developmental studies; DNT (developmental neurotoxicity): dose period occurs anytime during development (i.e. in utero, lactational, adolescent [after weaning, before adulthood]). Study contains functional observation battery or other battery of behavioral testing that occurs during or after dosing, typically during adulthood. Pathology has specific interest in the brain (i.e. regions, morphology, biochemistry, etc.)")
    private String studyType;

    @Size(max = 64)
    @Column(name = "guideline_number", length = 64)
    @Schema(description = "Number associated with the guideline.OPP DERs follow the Series 870 - Health Effects Test Guidelines.")
    private String guidelineNumber;

    @Size(max = 512)
    @Column(name = "guideline_name", length = 512)
    @Schema(description = "Name of guideline, such as the particular Office of Chemical Safety and Pollution Prevention (OCSPP) guideline or NTP specifications.")
    private String guidelineName;

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

    @Size(max = 64)
    @Column(name = "status", length = 64)
    @Schema(description = "The status regarding whether or not an endpoint was required, recommended, triggered, not required, or mentioned by the study's most closely related guideline. Assumes that an endpoint was tested if the guideline the study adheres to requires that endpoint to be tested.")
    private String status;

    @Column(name = "default_status")
    @Schema(description = "The status regarding whether or not an endpoint was tested and reported in a study. Assumes that an endpoint was tested if the guideline the study adheres to requires that endpoint to be tested.")
    private Boolean defaultStatus;

    @Column(name = "tested_status")
    @Schema(description = "Indicates if an endpoint was tested (1) or not tested (0). If an endpoint was tested, it was examined or measured in the study.")
    private Boolean testedStatus;

    @Column(name = "reported_status")
    @Schema(description = "Indicates if an endpoint was reported (1) or not reported (0). If an endpoint was reported, it appears somewhere in the text of the report.")
    private Boolean reportedStatus;

    @Column(name = "export_date")
    @Schema(description = "Date view of ToxRefDB data was created for API")
    private LocalDate exportDate;

    @Column(name = "data_version", length = Integer.MAX_VALUE)
    @Schema(description = "Version of ToxRefDB data")
    private String version;

}
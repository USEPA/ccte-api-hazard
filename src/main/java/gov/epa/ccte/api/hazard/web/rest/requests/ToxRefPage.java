package gov.epa.ccte.api.hazard.web.rest.requests;

import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder
public class ToxRefPage {
	private String studyType;
    private Long totalRecords;
    private Integer recordsOnPage;
    private Integer pageNumber;
    private Integer totalPages;
    private List<?> data;
}

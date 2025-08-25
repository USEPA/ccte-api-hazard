package gov.epa.ccte.api.hazard.web.rest;

import org.junit.jupiter.api.BeforeEach;

//This will test REST end-points in the ToxRefObsResource.java using WebMvcTest and MockitoBean

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.when;

import gov.epa.ccte.api.hazard.domain.ToxRefObs;
import gov.epa.ccte.api.hazard.repository.ToxRefObsRepository;
import gov.epa.ccte.api.hazard.service.ToxRefService;
import gov.epa.ccte.api.hazard.web.rest.requests.ToxRefPage;

import java.time.LocalDate;
import java.util.*;

@ActiveProfiles("test")
@WebMvcTest(ToxRefObsResource.class)
@RunWith(MockitoJUnitRunner.class)
class ToxRefObsResourceTest {

    @Autowired
    private MockMvc mockMvc;
    
    @MockitoBean
    private ToxRefObsRepository toxRefObsRepository;
    @MockitoBean 
    private ToxRefService toxRefService;
    
    private ToxRefObs toxRefObs;
    private ToxRefPage studyType;
    
    @BeforeEach
    void setUp() {
    	toxRefObs = ToxRefObs.builder()
    			.id(594893)
    			.studyId(2122)
    			.dtxsid("DTXSID9020112")
    			.casrn("1912-24-9")
    			.name("Atrazine")
    			.studyType("CHR")
    			.guidelineNumber("870.4200")
    			.guidelineName("Carcinogenicty")
    			.endpointCategory("systemic")
    			.endpointType("hematology")
    			.endpointTarget("blood clotting")
    			.status("not tested")
    			.defaultStatus(true)
    			.testedStatus(false)
    			.reportedStatus(true)
    			.exportDate(LocalDate.of(2024,12,02))
    			.version("v2_1")
    			.build();
    
    	studyType = ToxRefPage.builder()
    			.studyType("CHR")
    			.data(Collections.singletonList(toxRefObs))
    			.recordsOnPage(10000)
    			.totalRecords(569936L)
    			.pageNumber(1)
    			.totalPages(57)
    			.build();
    }
    
    @Test
    void testGetToxRefObsByStudyId() throws Exception {
        final List<ToxRefObs> data = Collections.singletonList(toxRefObs);

        when(toxRefObsRepository.findAllByStudyId(2122, ToxRefObs.class)).thenReturn(data);

        mockMvc.perform(get("/hazard/toxref/observations/search/by-study-id/{studyId}", "2122"))
				.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].studyId").value(toxRefObs.getStudyId()));
	}
    
    @Test
    void testGetToxRefObsByDtxsid() throws Exception {
        final List<ToxRefObs> data = Collections.singletonList(toxRefObs);

        when(toxRefObsRepository.findAllByDtxsid("DTXSID9020112", ToxRefObs.class)).thenReturn(data);

        mockMvc.perform(get("/hazard/toxref/observations/search/by-dtxsid/{dtxsid}", "DTXSID9020112"))
				.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].dtxsid").value(toxRefObs.getDtxsid()));
	}
    
    @Test
    void testGetToxRefObsByStudyType() throws Exception {
        Integer pageNumber = 1;
        Integer pageSize = 10000;
        Pageable pageable = PageRequest.of(pageNumber-1, pageSize);

        when(toxRefService.getAllToxRefObsByStudyType("CHR", pageSize, pageNumber, pageable)).thenReturn(studyType);

        mockMvc.perform(get("/hazard/toxref/observations/search/by-study-type/{studyType}", "CHR"))
				.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].studyType").value(studyType.getStudyType()));
    }
    
}

package gov.epa.ccte.api.hazard.web.rest;

import org.junit.jupiter.api.BeforeEach;

// This unit test validates GenetoxResource behavior with mocked repositories

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

import gov.epa.ccte.api.hazard.domain.GenetoxSummary;
import gov.epa.ccte.api.hazard.projection.CcdGenetoxDetail;
import gov.epa.ccte.api.hazard.repository.GenetoxDetailRepository;
import gov.epa.ccte.api.hazard.repository.GenetoxSummaryRepository;

import java.util.*;

@MockitoSettings(strictness = Strictness.WARN)
@ExtendWith(MockitoExtension.class)
class GenetoxResourceTest {

    @Mock
    private GenetoxDetailRepository genetoxDetailRepository;

    @Mock
    private GenetoxSummaryRepository genetoxSummaryRepository;

    private GenetoxResource genetoxResource;
    private CcdGenetoxDetail genetoxDetail;
    private GenetoxSummary genetoxSummary;
    private CcdGenetoxDetail ccdGenetoxDetail;
    private ProjectionFactory factory = new SpelAwareProxyProjectionFactory();

    @BeforeEach
    void setUp() {
        genetoxResource = new GenetoxResource(genetoxDetailRepository, genetoxSummaryRepository);
        ReflectionTestUtils.setField(genetoxResource, "batchSize", 200);

        //genetox detail is using a projection by default and does not display full domain
        genetoxDetail = factory.createProjection(CcdGenetoxDetail.class);
        genetoxDetail.setYear(null);
        genetoxDetail.setSource("NTP");
        genetoxDetail.setMetabolicActivation(null);
        genetoxDetail.setDtxsid("DTXSID7020182");
        genetoxDetail.setSpecies("Not specified");
        genetoxDetail.setAssayResult("negative");
        genetoxDetail.setAssayCategory("in vitro");
        genetoxDetail.setAssayType("Ames");
        genetoxDetail.setStrain(null);

        genetoxSummary = GenetoxSummary.builder()
                .id(9861)
                .ames("negative")
                .clowderDocId("https://clowder.edap-cluster.com/files/680b9b14e4b096bca880a4c7")
                .dtxsid("DTXSID7020182")
                .genetoxCall("negative")
                .genetoxSummaryId(9861)
                .micronucleus("negative")
                .reportsNegative(3)
                .reportsOther(0)
                .reportsPositive(0)
                .build();

        //the difference between with/without specified projection is a concatinated String for 'assayType'
        ccdGenetoxDetail = factory.createProjection(CcdGenetoxDetail.class);
        ccdGenetoxDetail.setYear(null);
        ccdGenetoxDetail.setSource("NTP");
        ccdGenetoxDetail.setMetabolicActivation(null);
        ccdGenetoxDetail.setDtxsid("DTXSID7020182");
        ccdGenetoxDetail.setSpecies("Not specified");
        ccdGenetoxDetail.setAssayResult("negative");
        ccdGenetoxDetail.setAssayCategory("in vitro");
        ccdGenetoxDetail.setAssayType("Ames | bacterial reverse mutation test");
        ccdGenetoxDetail.setStrain(null);
    }


    @Test
    void testGetGenetoxSummaryByDtxsid() {
        final List<GenetoxSummary> summary = Collections.singletonList(genetoxSummary);

        when(genetoxSummaryRepository.findByDtxsid("DTXSID7020182", GenetoxSummary.class)).thenReturn(summary);

        List<GenetoxSummary> result = genetoxResource.genetoxSummaryByDtxsid("DTXSID7020182");

        assertFalse(result.isEmpty());
        assertEquals(genetoxSummary.getDtxsid(), result.get(0).getDtxsid());
    }

    @Test
    void testGetGenetoxSummaryByBatchDtxsid() {
        final List<GenetoxSummary> summary = Collections.singletonList(genetoxSummary);
        String[] jsonArray = {"DTXSID7020182"};

        when(genetoxSummaryRepository.findByDtxsidInOrderByDtxsidAsc(jsonArray, GenetoxSummary.class)).thenReturn(summary);

        List<GenetoxSummary> result = genetoxResource.batchSearchSummary(jsonArray);

        assertFalse(result.isEmpty());
        assertEquals(genetoxSummary.getDtxsid(), result.get(0).getDtxsid());
    }

    // *********************** Summary - End *************************************

    // *********************** Detail - start *************************************


    @Test
    void testGetGenetoxDetailsByDtxsid() {
        final List<CcdGenetoxDetail> details = Collections.singletonList(genetoxDetail);

        when(genetoxDetailRepository.findByDtxsidOrderBySourceAsc("DTXSID7020182", CcdGenetoxDetail.class)).thenReturn(details);

        List<?> result = genetoxResource.getGenetoxDetailsByDtxsid("DTXSID7020182", null);

        assertFalse(result.isEmpty());
        assertEquals(genetoxDetail.getDtxsid(), ((CcdGenetoxDetail) result.get(0)).getDtxsid());
    }

    @Test
    void testGetGenetoxDetailsByDtxsidCCD() {
        final List<CcdGenetoxDetail> details = Collections.singletonList(ccdGenetoxDetail);

        when(genetoxDetailRepository.findByDtxsidWithConcatenatedColumn("DTXSID7020182")).thenReturn(details);

        List<?> result = genetoxResource.getGenetoxDetailsByDtxsid("DTXSID7020182", "ccd-genetox-details");

        assertFalse(result.isEmpty());
        assertEquals(ccdGenetoxDetail.getDtxsid(), ((CcdGenetoxDetail) result.get(0)).getDtxsid());
        assertFalse(((CcdGenetoxDetail) result.get(0)).getAssayType().isBlank());
    }

    @Test
    void testGenetoxDetailsByBatchDtxsid() {
        final List<CcdGenetoxDetail> details = Collections.singletonList(genetoxDetail);
        String[] jsonArray = {"DTXSID7020182"};

        when(genetoxDetailRepository.findByDtxsidInOrderByDtxsidAsc(jsonArray, CcdGenetoxDetail.class)).thenReturn(details);

        List<CcdGenetoxDetail> result = genetoxResource.batchSearch(jsonArray);

        assertFalse(result.isEmpty());
        assertEquals(genetoxDetail.getDtxsid(), result.get(0).getDtxsid());
    }
}
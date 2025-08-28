package gov.epa.ccte.api.hazard.web.rest;

import org.junit.jupiter.api.BeforeEach;

//This will test REST end-points in the AdmeResource.java using WebMvcTest and MockitoBean

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.when;

import gov.epa.ccte.api.hazard.domain.Adme;
import gov.epa.ccte.api.hazard.projection.CcdADME;
import gov.epa.ccte.api.hazard.repository.AdmeRepository;

import java.util.*;

@ActiveProfiles("test")
@WebMvcTest(AdmeResource.class)
@RunWith(MockitoJUnitRunner.class)
class AdmeResourceTest {
	
    @Autowired
    private MockMvc mockMvc;
    
    @MockitoBean
    private AdmeRepository admeRepository;
    
    private Adme adme;
    private CcdADME ccdADME;
    private ProjectionFactory factory = new SpelAwareProxyProjectionFactory();
    
    @BeforeEach
    void setUp() {
    	
    	adme = Adme.builder()
    			.id(104519)
    			.dtxsid("DTXSID7020182")
    			.description("Clint")
    			.measured("19.9")
    			.predicted("NA")
    			.unit("uL/min/million hepatocytes")
    			.model("NA")
    			.reference("https://doi.org/10.1093/toxsci/kfz205")
    			.percentile("NA")
    			.species("Human")
    			.dataSourceSpecies("Human")
    			.build();
    	
    	ccdADME = factory.createProjection(CcdADME.class);
    	ccdADME.setLabel("Intrinsic Hepatic Clearance");
    	ccdADME.setDtxsid("DTXSID7020182");
    	ccdADME.setDescription("Intrinsic hepatic clearance characterizes the volume of blood cleared by the metabolism of a million hepatocytes. This value can be scaled to predict in vivo clearance using the cellular density of, volume of, and blood flow to the liver.");
    	ccdADME.setMeasured("19.9");
    	ccdADME.setPredicted("NA");
    	ccdADME.setUnit("uL/min/million hepatocytes");
    	ccdADME.setModel("NA");
    	ccdADME.setReference("https://doi.org/10.1093/toxsci/kfz205");
    	ccdADME.setPercentile("NA");
    	ccdADME.setSpecies("Human");
    	ccdADME.setDataSourceSpecies("Human");
    	
    }

    @Test
    void testGetAdmeIviveByDtxsid() throws Exception {
        final List<Adme> ivive = Collections.singletonList(adme);

        when(admeRepository.findByDtxsid("DTXSID7020182", Adme.class)).thenReturn(ivive);

        mockMvc.perform(get("/hazard/adme-ivive/search/by-dtxsid/{dtxsid}", "DTXSID7020182"))
				.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].dtxsid").value(adme.getDtxsid()));
    
    }
    
    @Test
    void testGetAdmeIviveByDtxsidCCD() throws Exception {
        final List<CcdADME> ivive = Collections.singletonList(ccdADME);

        when(admeRepository.findByDtxsidWithLabelColumn("DTXSID7020182")).thenReturn(ivive);

        mockMvc.perform(get("/hazard/adme-ivive/search/by-dtxsid/{dtxsid}", "DTXSID7020182")
        		.param("projection", "ccd-adme-data"))
				.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].dtxsid").value(ccdADME.getDtxsid()));    
    }
}

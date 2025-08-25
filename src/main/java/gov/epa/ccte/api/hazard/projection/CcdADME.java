package gov.epa.ccte.api.hazard.projection;

public interface CcdADME {

	String getLabel();
	String getDtxsid();
	String getDescription();
	String getMeasured();
	String getPredicted();
	String getUnit();
	String getModel();
	String getReference();
	String getPercentile();
	String getSpecies();
	String getDataSourceSpecies();
	
}

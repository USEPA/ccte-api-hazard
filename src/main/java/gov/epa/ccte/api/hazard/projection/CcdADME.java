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
	
	Void setLabel(String label);
	Void setDtxsid(String dtxsid);
	Void setDescription(String description);
	Void setMeasured(String measured);
	Void setPredicted(String predicted);
	Void setUnit(String unit);
	Void setModel(String model);
	Void setReference(String reference);
	Void setPercentile(String percentile);
	Void setSpecies(String species);
	Void setDataSourceSpecies(String dataSourceSpecies);
}

package gov.epa.ccte.api.hazard.projection;

public interface CcdGenetoxDetail {

	String getDtxsid();
	String getSource();
	String getAssayCategory();
	String getAssayType();
	String getMetabolicActivation();
	String getSpecies();
	String getStrain();
	String getAssayResult();
	Integer getYear();
	
	Void setDtxsid(String dtxsid);
	Void setSource(String source);
	Void setAssayCategory(String assayCategory);
	Void setAssayType(String assayType);
	Void setMetabolicActivation(String metabolicActivation);
	Void setSpecies(String species);
	Void setStrain(String strain);
	Void setAssayResult(String assayResult);
	Void setYear(Integer year);

}

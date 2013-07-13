package org.sgx.madrenecesidad.client.ui.search;

import java.util.Map;

import org.sgx.jsutil.client.appstate.AppStateParamHelper;

public class PlaceSearchModel {
String keywords; 
boolean inCurrentMapView;
public String getKeywords() {
	return keywords;
}
public void setKeywords(String keywords) {
	this.keywords = keywords;
}
public boolean isInCurrentMapView() {
	return inCurrentMapView;
}
public void setInCurrentMapView(boolean inCurrentMapView) {
	this.inCurrentMapView = inCurrentMapView;
} 

/**
 * will return a new model or null if some of the required attrs is missing
 * @param stateConfig
 * @return
 */
public static PlaceSearchModel fromStateConfig(String stateConfig) {
	if(stateConfig==null)
		return null; 
	Map<String, String> c = AppStateParamHelper.toParams(stateConfig);
	if(!c.containsKey("keywords"))
		return null; 
	PlaceSearchModel psm = new PlaceSearchModel(); 
	psm.setKeywords(c.get("keywords")==null ? "" : c.get("keywords"));
	return psm; 
}
}

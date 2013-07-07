package org.sgx.madrenecesidad.client.ui.editors.model;

import java.util.Map;

import org.sgx.jsutil.client.appstate.AppStateParamHelper;

public class MapViewSearchModel {
String keywords;
//
//private MapViewSearchModel(String keywords) {
//	super();
//	this.keywords = keywords;
//}

public String getKeywords() {
	return keywords;
}

public void setKeywords(String keywords) {
	this.keywords = keywords;
}

public static MapViewSearchModel fromStateConfig(String stateConfig) {
	if(stateConfig==null)
		return null; 
	Map<String, String> c = AppStateParamHelper.toParams(stateConfig);
	if(!c.containsKey("keywords"))
		return null; 
	MapViewSearchModel model = new MapViewSearchModel(); 
	model.setKeywords(c.get("keywords")==null ? "" : c.get("keywords"));
	return model; 
} 

}

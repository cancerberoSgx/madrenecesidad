package org.sgx.madrenecesidad.client.model.jso;

import com.google.gwt.core.client.JavaScriptObject;

public class MapViewJSO extends JavaScriptObject {
protected MapViewJSO(){}
public static final native MapViewJSO create()/*-{
	return {}; 
}-*/;
/**
 * 
 * @return
 */
public native final String name() /*-{
return this["name"]; 
}-*/;

/**
 * 
 * @param val
 * @return this - for setter chaining
 */
public native final MapViewJSO name(String val) /*-{
this["name"] = val; 
return this; 
}-*/;

/**
 * 
 * @return
 */
public native final String id() /*-{
return this["id"]+""; 
}-*/;

/**
 * 
 * @param val
 * @return this - for setter chaining
 */
public native final MapViewJSO id(String val) /*-{
this["id"] = val; 
return this; 
}-*/;

}

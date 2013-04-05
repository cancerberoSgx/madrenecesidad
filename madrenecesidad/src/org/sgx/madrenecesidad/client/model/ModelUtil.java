package org.sgx.madrenecesidad.client.model;

import java.util.Collection;
import java.util.List;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

public class ModelUtil {
public static JsArray<JavaScriptObject> toJSO(Collection<? extends JSOable> objs) {
	JsArray<JavaScriptObject> jsarr = JsArray.createArray().cast();
	for(JSOable jsoable : objs) {
		jsarr.push(jsoable.toJSO()); 
	}
	return jsarr; 
}
}

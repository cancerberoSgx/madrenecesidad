package org.sgx.madrenecesidad.client.util.jquery;

import com.google.gwt.core.client.JsArray;
import com.google.gwt.dom.client.Element;

public class JQuery {
public static final native JsArray<JQueryEl> find(Element root, String selector)/*-{
	$wnd.jQuery(root).find(selector); 
}-*/;

}

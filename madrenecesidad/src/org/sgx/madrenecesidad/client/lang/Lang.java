package org.sgx.madrenecesidad.client.lang;

import org.sgx.jsutil.client.JsObject;

import com.google.gwt.dom.client.Element;

public class Lang {
String name, description;
JsObject data;
public String get(String key) {
	return data.objGetString(key); 
}
public String get(String key, String def) {
	return  data.objGetString(key, def);	
}
public Lang(String name) {
	super();
	this.name = name;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public JsObject getData() {
	return data;
}
public void setData(JsObject data) {
	this.data = data;
} 
public void internationalizeHtml(Element e, String key) {
	e.setAttribute(LangManager.ATTRIBUTE_INNERHTML, key); 
	e.setInnerHTML(get(key)); 
}
public void internationalizeTitle(Element e, String key) {
	e.setAttribute(LangManager.ATTRIBUTE_TITLE, key); 
	e.setAttribute("title", get(key)); 
}
}

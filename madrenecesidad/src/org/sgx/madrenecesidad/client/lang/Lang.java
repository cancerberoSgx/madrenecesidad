package org.sgx.madrenecesidad.client.lang;

import org.sgx.jsutil.client.JsObject;

public class Lang {
String name, description;
JsObject data;
public String get(String key) {
	return data.objGetString(key); 
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

 
}

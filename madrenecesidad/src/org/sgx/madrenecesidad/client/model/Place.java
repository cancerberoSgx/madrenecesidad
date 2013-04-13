package org.sgx.madrenecesidad.client.model;

import java.io.Serializable;

import org.sgx.jsutil.client.JsObject;

import com.google.appengine.api.datastore.GeoPt;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.rpc.IsSerializable;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Parent;
/**
 * 
 * @author sg
 * 
 */
@Entity
//@Cache
public class Place implements Serializable, IsSerializable {
@Id Long id; 
GeoPt center; 
String name;
String description; 
String ownerId;
boolean moderated; 
boolean publishedInGoogle;

String indexId; 

//Key<WebSite> webSite; 
///**
// * owner channel of this places
// */
/*@Parent*/
//Key<Channel> channel;

public JavaScriptObject toJSO() {
	return JsObject.one("name", getName())._("description", getDescription()); 
}

public String getOwnerId() {
	return ownerId;
}

public void setOwnerId(String ownerId) {
	this.ownerId = ownerId;
}

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public GeoPt getCenter() {
	return center;
}

public void setCenter(GeoPt center) {
	this.center = center;
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

public boolean isModerated() {
	return moderated;
}

public void setModerated(boolean moderated) {
	this.moderated = moderated;
}

public boolean isPublishedInGoogle() {
	return publishedInGoogle;
}

public void setPublishedInGoogle(boolean publishedInGoogle) {
	this.publishedInGoogle = publishedInGoogle;
}

public String getIndexId() {
	return indexId;
}

public void setIndexId(String indexId) {
	this.indexId = indexId;
}
}

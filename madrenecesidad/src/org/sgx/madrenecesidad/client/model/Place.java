package org.sgx.madrenecesidad.client.model;

import java.io.Serializable;

import com.google.appengine.api.datastore.GeoPt;
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
GeoPt point; 
String name;
String description; 
boolean moderated; 
//Key<WebSite> webSite; 
/**
 * owner channel of this places
 */
/*@Parent*/
Key<Channel> channel;
}

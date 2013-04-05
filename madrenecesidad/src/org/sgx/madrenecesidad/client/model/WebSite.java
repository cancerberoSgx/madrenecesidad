package org.sgx.madrenecesidad.client.model;

import java.io.Serializable;

import com.google.appengine.api.datastore.Text;
import com.google.gwt.user.client.rpc.IsSerializable;
import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
/**
 * 
 * @author sg
 * 
 */
@Entity
//@Cache
public class WebSite implements Serializable, IsSerializable {
@Id Long id; 
/**
 * owner place
 */
Long placeId; 

Text html; 
}

package org.sgx.madrenecesidad.client.model;

import java.io.Serializable;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.rpc.IsSerializable;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.*;

/**
 * 
 * @author sg
 * 
 */
@Entity
//@Cache
public class Tag implements Serializable, IsSerializable {

	private static final long serialVersionUID = 1L;

	@Id
	Long id; 
	
	String name;

	String description;

	/**
	 * owner channel of this tag
	 */
	/*@Parent*/
//	@Index Key<Channel> channel;
	@Index String channel;  
	
	String indexId; 

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

//	public Key<Channel> getChannel() {
//		return channel;
//	}
//
//	public void setChannel(Key<Channel> channel) {
//		this.channel = channel;
//	}
//	public void setChannel(Channel channel) {
//		this.channel = Key.create(Channel.class, channel.getName()); 
//	}
	public void setDescription(String description) {
		this.description = description;
	}
	

	public String getIndexId() {
		return indexId;
	}

	public void setIndexId(String indexId) {
		this.indexId = indexId;
	}

	public native final JavaScriptObject toJSO()/*-{
		return {
			"name" : this.@org.sgx.madrenecesidad.client.model.Tag::getName()(),
			"description" : this.@org.sgx.madrenecesidad.client.model.Tag::getDescription()(),
		};
	}-*/;

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Tag && obj != null)
			return ((Tag) obj).getName().equals(getName());
		else
			return false;
	}

	public void copyFrom(Tag t) {
		setName(t.getName()); 
		setDescription(t.getDescription()); 
	}

	public void setChannel(Channel c) {
		this.channel = c.getName(); 
	}

}

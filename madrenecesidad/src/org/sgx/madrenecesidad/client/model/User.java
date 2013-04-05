package org.sgx.madrenecesidad.client.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Ignore;

/**
 * 
 * @author sg
 * 
 */
@Entity
// @Cache
public class User implements Serializable, IsSerializable{
	@Id
	String email;
	String nickname;
	/**
	 * only used in testing automation. Never stored.
	 */
	@Ignore
	String password;

	String indexId;

//	public User(String email) {
//		super();
//		this.email = email;
//	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIndexId() {
		return indexId;
	}

	public void setIndexId(String indexId) {
		this.indexId = indexId;
	}

	// List<Ref<MapView>> mapViews = new ArrayList<Ref<MapView>>();

	// public List<MapView> getMapViews() {
	// List<MapView> l = new LinkedList<MapView>();
	// for(Ref<MapView> k : mapViews) {
	// l.add(k.get());
	// }
	// return l;
	// }
	// public void setMapViews(List<MapView> l) {
	// mapViews.clear();
	// for(MapView mv : l) {
	// Key<MapView> key = Key.create(MapView.class, mv.getId());
	// mapViews.add(Ref.create(key));
	// }
	// }

}

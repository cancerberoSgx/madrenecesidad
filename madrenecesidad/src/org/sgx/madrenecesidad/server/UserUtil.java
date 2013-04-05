package org.sgx.madrenecesidad.server;

import org.sgx.madrenecesidad.client.model.Owned;

import com.google.appengine.api.users.User;

public class UserUtil {
User user; 
public UserUtil(User user) {
	this.user=user; 
}
public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}
public boolean userOwns(Owned o) {
	return o.getOwnerId().equals(user.getUserId()); 
}
}

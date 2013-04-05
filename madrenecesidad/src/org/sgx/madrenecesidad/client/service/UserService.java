package org.sgx.madrenecesidad.client.service;

import org.sgx.madrenecesidad.client.model.UserStateModel;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("userService")
public interface UserService extends RemoteService {
	UserStateModel getUserState();
}

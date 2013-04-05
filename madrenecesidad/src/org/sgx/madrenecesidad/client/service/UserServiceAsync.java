package org.sgx.madrenecesidad.client.service;

import org.sgx.madrenecesidad.client.model.UserStateModel;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface UserServiceAsync {

	void getUserState(AsyncCallback<UserStateModel> callback);

}

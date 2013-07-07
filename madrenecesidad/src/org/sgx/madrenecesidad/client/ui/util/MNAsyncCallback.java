package org.sgx.madrenecesidad.client.ui.util;

import org.sgx.madrenecesidad.client.MNMain;

import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class MNAsyncCallback<T> implements AsyncCallback<T>{

	String action; 
	
	public MNAsyncCallback(String action) {
		super();
		this.action = action;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	//it is not necessary that the user overrides this. 
	@Override
	public void onFailure(Throwable caught) {
		MNMain.layout().setStatusError(action, caught);
	}

	//TODO: use suoper.onSuccess and log something here ? 
//	@Override
//	public void onSuccess(T result) {
//		// TODO Auto-generated method stub
//		
//	}

}

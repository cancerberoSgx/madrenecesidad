package org.sgx.gwtpersistor.client;

public interface PersistorResponse {

	public static interface ErrorCallback{
		void onError(String msg, Throwable error); 
	}
	
	public static interface SuccessCallback<T>{
		void onSuccess(T response); 
	}
	
}

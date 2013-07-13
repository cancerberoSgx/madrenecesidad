package org.sgx.madrenecesidad.client.storage;

import org.sgx.jsutil.client.storage.CookieStorage;
import org.sgx.jsutil.client.storage.Storage;

public class StorageManager {
	Storage storage;
	public StorageManager(){
		storage = new CookieStorage("mn"); 
	}
	public Storage getStorage() {
		return storage;
	}
}

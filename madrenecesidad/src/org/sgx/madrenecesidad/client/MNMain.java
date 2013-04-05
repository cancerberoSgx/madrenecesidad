package org.sgx.madrenecesidad.client;

import java.util.LinkedList;
import java.util.List;

import org.sgx.jsutil.client.SimpleCallback;
import org.sgx.madrenecesidad.client.ui.AppMain;

public class MNMain {
	private static MNMain instance;

	private MNMain() {
		afterAttachListeners=new LinkedList<SimpleCallback>(); 
	}

	public static MNMain getInstance() {
		if (instance == null)
			instance = new MNMain();
		return instance;
	}

	List<SimpleCallback> afterAttachListeners;
	AppMain layout; 

	/**
	 * @param e
	 * @return
	 * @see java.util.List#add(java.lang.Object)
	 */
	public boolean addAfterAttachListener(SimpleCallback e) {
		return afterAttachListeners.add(e);
	} 
	
	public void notifyAfterAttach() {
		for(SimpleCallback c : afterAttachListeners) {
			c.call(); 
		}
	}

	public AppMain getLayout() {
		return layout;
	}

	public void setLayout(AppMain layout) {
		this.layout = layout;
	}
	
}

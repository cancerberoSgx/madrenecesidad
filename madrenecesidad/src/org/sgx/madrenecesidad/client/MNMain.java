package org.sgx.madrenecesidad.client;

import java.util.LinkedList;
import java.util.List;

import org.sgx.jsutil.client.SimpleCallback;
import org.sgx.madrenecesidad.client.service.MNServiceFactory;
import org.sgx.madrenecesidad.client.ui.AppMain;
import org.sgx.madrenecesidad.client.ui.state.MNStateManager;
import org.sgx.madrenecesidad.client.ui.view.MNViewManager;

public class MNMain {
	private static MNMain instance;

	private MNMain() {
		afterAttachListeners=new LinkedList<SimpleCallback>();
		serviceFactory = MNServiceFactory.getInstance(); 
		viewManager = MNViewManager.getInstance(); 
	}

	public static MNMain getInstance() {
		if (instance == null)
			instance = new MNMain();
		return instance;
	}

	List<SimpleCallback> afterAttachListeners;
	AppMain layout; 
	MNStateManager stateManager; 
	MNServiceFactory serviceFactory; 
	MNViewManager viewManager; 
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

	public MNServiceFactory getServiceFactory() {
		return serviceFactory;
	}
	public MNViewManager getViewManager() {
		return viewManager;
	}
	
	public AppMain getLayout() {
		return layout;
	}

	public void setLayout(AppMain layout) {
		this.layout = layout;
	}

	public MNStateManager getStateManager() {
		return stateManager;
	}
	public void setStateManager(MNStateManager stateManager) {
		this.stateManager = stateManager;
	}
}

package org.sgx.madrenecesidad.client;

import java.util.LinkedList;
import java.util.List;

import org.sgx.jsutil.client.SimpleCallback;
import org.sgx.jsutil.client.storage.Storage;
import org.sgx.madrenecesidad.client.geolocation.GeoLocationManager;
import org.sgx.madrenecesidad.client.lang.Lang;
import org.sgx.madrenecesidad.client.lang.LangManager;
import org.sgx.madrenecesidad.client.service.MNServiceFactory;
import org.sgx.madrenecesidad.client.state.MNStateManager;
import org.sgx.madrenecesidad.client.storage.StorageManager;
import org.sgx.madrenecesidad.client.ui.AppMain;
import org.sgx.madrenecesidad.client.ui.LayerManager;
import org.sgx.madrenecesidad.client.ui.view.MNViewManager;

public class MNMain {
	private static MNMain instance;
	private GeoLocationManager geoManager;

	private MNMain() {
		afterAttachListeners=new LinkedList<SimpleCallback>();
		serviceFactory = MNServiceFactory.getInstance(); 
		viewManager = MNViewManager.getInstance();
		mapLayerManager = new LayerManager(); 
		langManager = new LangManager(); 
		storageManager = new StorageManager(); 
		geoManager = new GeoLocationManager(); 
	}

	public static AppMain layout() {
		return getInstance().getLayout(); 
	}
	public static MNServiceFactory services() {
		return getInstance().getServiceFactory(); 
	}
	public static LayerManager mapLayers() {
		return getInstance().getMapLayerManager(); 
	}
	public static MNStateManager states() {
		return getInstance().getStateManager(); 
	}
	public static GeoLocationManager geo() {
		return getInstance().getGeoManager();  
	}
	public static Storage storage() {
		return getInstance().getStorageManager().getStorage(); 
	}
	/**
	 * get current lang dict
	 */
	public static Lang lang() {
		return getInstance().getLangManager().getCurrentLang(); 
	}
	/**
	 * get current lang dict
	 */
	public static LangManager langManager() {
		return getInstance().getLangManager(); 
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
	LangManager langManager; 
	StorageManager storageManager; 
	LayerManager mapLayerManager;
	
	public LayerManager getMapLayerManager() {
		return mapLayerManager;
	}
	public StorageManager getStorageManager() {
		return storageManager;
	}
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
	public GeoLocationManager getGeoManager() {
		return geoManager;
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
	public LangManager getLangManager() {
		return langManager;
	}
}

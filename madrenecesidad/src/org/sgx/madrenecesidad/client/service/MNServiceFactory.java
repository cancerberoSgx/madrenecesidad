package org.sgx.madrenecesidad.client.service;

import com.google.gwt.core.client.GWT;
/**
 * 
 * @author sg
 *
 */
public class MNServiceFactory {

	private static MNServiceFactory instance = new MNServiceFactory();

	private UserServiceAsync userService = null;
	private MapViewServiceAsync mapViewService = null;
	private PlaceServiceAsync placeService = null;

	private MNServiceFactory() {
		super();
	}

	public static MNServiceFactory getInstance() {
		return instance;
	}

	public UserServiceAsync getUserService() {
		if (userService == null)
			userService = GWT.create(UserService.class);
		return userService;
	}

	public MapViewServiceAsync getMapViewService() {
		if (mapViewService == null)
			mapViewService = GWT.create(MapViewService.class);
		return mapViewService;
	}

	public PlaceServiceAsync getPlaceService() {
		if (placeService == null)
			placeService = GWT.create(PlaceService.class);
		return placeService;
	}
}

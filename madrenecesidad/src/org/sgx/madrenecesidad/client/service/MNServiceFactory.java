package org.sgx.madrenecesidad.client.service;

import com.google.gwt.core.client.GWT;

public class MNServiceFactory {

private static MNServiceFactory instance = new MNServiceFactory();

private UserServiceAsync userService = GWT.create(UserService.class);
private MapViewServiceAsync mapViewService = GWT.create(MapViewService.class);

private MNServiceFactory() {
	super();
}

public static MNServiceFactory getInstance() {
	return instance;
}

public UserServiceAsync getUserService() {
	return userService;
}
public MapViewServiceAsync getMapViewService() {
	return mapViewService;
}
}

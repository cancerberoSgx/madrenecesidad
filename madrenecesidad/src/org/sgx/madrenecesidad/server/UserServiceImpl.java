package org.sgx.madrenecesidad.server;

import java.util.logging.Logger;

import org.sgx.madrenecesidad.client.model.UserStateModel;

public class UserServiceImpl extends AbstractService implements org.sgx.madrenecesidad.client.service.UserService {
	private static final Logger LOG = Logger.getLogger(UserServiceImpl.class.getName());
	
	@Override
	public UserStateModel getUserState() {
		UserStateModel s = new UserStateModel();
		MapViewServiceImpl mavViewService = new MapViewServiceImpl(); 
		s.setMapViews(mavViewService.list());		
		return s; 		
	}

	@Override
	public Logger LOG() {
		return LOG;
	}

}

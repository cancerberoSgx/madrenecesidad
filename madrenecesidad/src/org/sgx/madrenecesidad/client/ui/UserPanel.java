package org.sgx.madrenecesidad.client.ui;

import org.sgx.jsutil.client.DOMUtil;
import org.sgx.madrenecesidad.client.MNMain;
import org.sgx.madrenecesidad.client.UserInfo;
import org.sgx.madrenecesidad.client.util.MNUtil;

import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.geolocation.client.Geolocation;
import com.google.gwt.geolocation.client.Position;
import com.google.gwt.geolocation.client.Position.Coordinates;
import com.google.gwt.geolocation.client.PositionError;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.UIObject;

public class UserPanel extends UIObject {

	private static UserPanelUiBinder uiBinder = GWT.create(UserPanelUiBinder.class);

	interface UserPanelUiBinder extends UiBinder<Element, UserPanel> {
	}
	
	@UiField Element loggedInPanel, notLoggedInPanel, userName; 
	@UiField AnchorElement loginAnchor, locateMeAnchor, locateMeLoggedInAnchor; 

	public UserPanel() {
		setElement(uiBinder.createAndBindUi(this));
		UserInfo user = MNUtil.getUser();
		MNUtil.fixUserUrls(user); 
		
		if (user != null && user.isLoggedIn()) {
			notLoggedInPanel.removeFromParent(); 
			userName.setInnerText(user.nickname()); 
		}
		
		else {
			loggedInPanel.removeFromParent(); 
			loginAnchor.setHref(user.loginUrl()); 
		}
		DOMUtil.addClickHandler(locateMeAnchor, new DOMUtil.EventHandler() {			
			@Override
			public void onEvent(Event event) {
				doGeoLocateMe();
			}
		}); 
		DOMUtil.addClickHandler(locateMeLoggedInAnchor, new DOMUtil.EventHandler() {			
			@Override
			public void onEvent(Event event) {
				doGeoLocateMe();
			}
		});
		
	}

	protected void doGeoLocateMe() {
		Geolocation geo = MNMain.geo().getGeo();
		if(geo==null) {
			Window.alert("Your browser don't have support for Geo Location, sorry :(");
			return; 
		}
		geo.getCurrentPosition(new Callback<Position, PositionError>() {
			
			@Override
			public void onSuccess(Position result) {
				Coordinates coords = result.getCoordinates();
				LatLng center = LatLng.newInstance(coords.getLatitude(), coords.getLongitude()); 
				MNMain.layout().getMapWidget().setCenter(center); 
			}
			
			@Override
			public void onFailure(PositionError reason) {
				Window.alert("Sorry ,for some reason we cannot locate you :( Error reason: "+reason);
				return; 
			}
		}); 
			
	}

}

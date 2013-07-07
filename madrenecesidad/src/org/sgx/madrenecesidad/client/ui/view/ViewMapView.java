package org.sgx.madrenecesidad.client.ui.view;

import org.sgx.madrenecesidad.client.model.MapView;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.UIObject;

public class ViewMapView extends UIObject {

	private static ViewMapViewUiBinder uiBinder = GWT.create(ViewMapViewUiBinder.class);

	interface ViewMapViewUiBinder extends UiBinder<Element, ViewMapView> {
	}

	@UiField Element latitude, longitude, desc, name; 
	
	public ViewMapView() {
		setElement(uiBinder.createAndBindUi(this));
	}
	
	public void load(MapView mv) {
		
	}

}

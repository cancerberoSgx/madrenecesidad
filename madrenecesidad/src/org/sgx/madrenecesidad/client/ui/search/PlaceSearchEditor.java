package org.sgx.madrenecesidad.client.ui.search;

import org.sgx.madrenecesidad.client.model.Place;
import org.sgx.madrenecesidad.client.ui.editors.MNEditor;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.UIObject;

public class PlaceSearchEditor extends UIObject implements MNEditor<PlaceSearchModel>{

	private static PlaceSearchEditorUiBinder uiBinder = GWT.create(PlaceSearchEditorUiBinder.class);

	interface PlaceSearchEditorUiBinder extends UiBinder<Element, PlaceSearchEditor> {
	}

	@UiField InputElement inCurrentMapView, keywordsEntry; 
	
	public PlaceSearchEditor() {
		setElement(uiBinder.createAndBindUi(this));
	}

	@Override
	public PlaceSearchModel flush() {
		PlaceSearchModel m = new PlaceSearchModel(); 
		m.setKeywords(keywordsEntry.getValue()); 
		m.setInCurrentMapView(inCurrentMapView.isChecked()); 
		return m;
	}

	@Override
	public void load(PlaceSearchModel t) {
		keywordsEntry.setValue(t.getKeywords()); 
		inCurrentMapView.setChecked(t.isInCurrentMapView()); 
	}

}

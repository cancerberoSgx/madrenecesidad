package org.sgx.madrenecesidad.client.ui.editors;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.UIObject;

public class MapViewSearchEditor extends UIObject implements MNEditor<MapViewSearchModel>{

	private static MapViewSearchEditorUiBinder uiBinder = GWT.create(MapViewSearchEditorUiBinder.class);

	interface MapViewSearchEditorUiBinder extends UiBinder<Element, MapViewSearchEditor> {
	}
	
	@UiField InputElement keywordsEntry; 

	public MapViewSearchEditor() {
		setElement(uiBinder.createAndBindUi(this));
	}

	@Override
	public MapViewSearchModel flush() {
		MapViewSearchModel m = new MapViewSearchModel(); 
		m.setKeywords(keywordsEntry.getValue()); 
		return m; 
	}

	@Override
	public void load(MapViewSearchModel t) {
		keywordsEntry.setValue(t.getKeywords());
	}

}

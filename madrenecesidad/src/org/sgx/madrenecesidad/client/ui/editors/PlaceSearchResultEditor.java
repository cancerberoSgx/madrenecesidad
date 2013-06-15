package org.sgx.madrenecesidad.client.ui.editors;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.UIObject;

public class PlaceSearchResultEditor extends UIObject {

	private static PlaceSearchResultEditorUiBinder uiBinder = GWT.create(PlaceSearchResultEditorUiBinder.class);

	interface PlaceSearchResultEditorUiBinder extends UiBinder<Element, PlaceSearchResultEditor> {
	}

	public PlaceSearchResultEditor() {
		setElement(uiBinder.createAndBindUi(this));
	}

}

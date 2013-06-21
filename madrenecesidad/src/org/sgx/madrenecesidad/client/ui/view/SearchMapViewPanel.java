package org.sgx.madrenecesidad.client.ui.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.UIObject;

public class SearchMapViewPanel extends UIObject {

	private static SearchMapViewPanelUiBinder uiBinder = GWT.create(SearchMapViewPanelUiBinder.class);

	interface SearchMapViewPanelUiBinder extends UiBinder<Element, SearchMapViewPanel> {
	}

	@UiField Element resultsEl, keywordsEl; 
	public SearchMapViewPanel() {
		setElement(uiBinder.createAndBindUi(this));
	}

}

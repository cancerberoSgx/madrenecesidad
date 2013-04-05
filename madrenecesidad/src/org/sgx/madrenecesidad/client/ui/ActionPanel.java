package org.sgx.madrenecesidad.client.ui;

import org.sgx.jsutil.client.DOMUtil;
import org.sgx.madrenecesidad.client.ui.action.MeasureDistanceAction;
import org.sgx.madrenecesidad.client.ui.action.SearchAddressAction;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.UIObject;

public class ActionPanel extends UIObject {

	private static ActionPanelUiBinder uiBinder = GWT.create(ActionPanelUiBinder.class);

	interface ActionPanelUiBinder extends UiBinder<Element, ActionPanel> {
	}
	
	@UiField Element actionSearchAddressAnchor, actionMeasureDistanceAnchor; 

	public ActionPanel() {
		setElement(uiBinder.createAndBindUi(this));
		DOMUtil.addClickHandler(actionSearchAddressAnchor, new DOMUtil.EventHandler() {			
			@Override
			public void onEvent(Event event) {
				new SearchAddressAction().perform(null); 
			}
		}); 
		DOMUtil.addClickHandler(actionMeasureDistanceAnchor, new DOMUtil.EventHandler() {			
			@Override
			public void onEvent(Event event) {
				new MeasureDistanceAction().perform(null); 
			}
		}); 
		
	}

}

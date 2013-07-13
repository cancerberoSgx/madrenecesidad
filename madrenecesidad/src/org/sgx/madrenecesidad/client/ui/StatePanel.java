package org.sgx.madrenecesidad.client.ui;

import java.util.HashMap;
import java.util.Map;

import org.sgx.jsutil.client.DOMUtil;
import org.sgx.jsutil.client.JsObject;
import org.sgx.jsutil.client.JsUtil;
import org.sgx.jsutil.client.appstate.AppState;
import org.sgx.madrenecesidad.client.MNMain;
import org.sgx.madrenecesidad.client.util.bootstrap.Bootstrap;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.UListElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.UIObject;

public class StatePanel extends UIObject {

private static StatePanelUiBinder uiBinder = GWT.create(StatePanelUiBinder.class);

interface StatePanelUiBinder extends UiBinder<Element, StatePanel> {
}

@UiField UListElement ul; 
@UiField DivElement content; 

Map<String, AppState> states;
Map<String, Element> anchorTabs;
Map<String, Element> contentEls; 

public StatePanel() {
	setElement(uiBinder.createAndBindUi(this));
	states=new HashMap<String, AppState>(); 
	anchorTabs=new HashMap<String, Element>(); 
	contentEls = new HashMap<String, Element>(); 
}

public void setState(final AppState state, UIObject view) {	
	if(view != null && !states.containsKey(state.getName())) {
		states.put(state.getName(), state); 
//		String id=state.getName(); 
		String stateName = MNMain.lang().get(state.getName(), state.getName()); 
		JsArray<Element> insertedEls = DOMUtil.appendTemplateAsNode(ul, 
			"<a>%text%</a>", "text", stateName); 		
		Element tabEl = insertedEls.get(0); 
		anchorTabs.put(state.getName(), tabEl);
		DOMUtil.addClickHandler(tabEl, new DOMUtil.EventHandler() {			
			@Override
			public void onEvent(Event e) {
				e.preventDefault();		
				MNMain.getInstance().getStateManager().navigate(state, ""); 
			}
		}); 
		
		insertedEls = DOMUtil.appendTemplateAsNode(content, 
			"<div class='tab-pane active'></div> ");
		
		Element contentEl = insertedEls.get(0); 		
		contentEl.appendChild(view.getElement()); 
		contentEls.put(state.getName(), contentEl);
		
		showTabContent(state); 

	}
	else {
		showTabContent(state); 
	}
}

private void showTabContent(AppState state) {
	if(state==null||contentEls.get(state.getName())==null||anchorTabs.get(state.getName())==null) {
		System.out.println("EXITING: "+state);
		return; 
	}
	for(Element el : contentEls.values()) 
		DOMUtil.hide(el); 	
	DOMUtil.show(contentEls.get(state.getName())); 	
	for(Element el : anchorTabs.values()) 
		el.removeClassName("active"); 
	anchorTabs.get(state.getName()).addClassName("active"); 
}
	
}

package org.sgx.madrenecesidad.client.ui.state;

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
		JsArray<Element> insertedEls = DOMUtil.appendTemplateAsNode(ul, 
			"<a>%text%</a>", "text", state.getName()); 		
		anchorTabs.put(state.getName(), insertedEls.get(0));
		DOMUtil.addClickHandler(insertedEls.get(0), new DOMUtil.EventHandler() {			
			@Override
			public void onEvent(Event e) {
//				e.preventDefault();
//				e.stopPropagation(); 
//				Window.alert("hello"); 				
				MNMain.getInstance().getStateManager().navigate(state, ""); 
			}
		}); 
		
		insertedEls = DOMUtil.appendTemplateAsNode(content, 
			"<div class='tab-pane active'></div> ");
		
		Element contentEl = insertedEls.get(0); 		
		contentEl.appendChild(view.getElement()); 
		contentEls.put(state.getName(), contentEl); ;
		
		showTabContent(state); 

//		Bootstrap.tabShow(anchorTabs.get(state.getName())); 
//		MNMain.getInstance().getStateManager().navigate(state, ""); 
	}
	else {
		showTabContent(state); 
//		Bootstrap.tabShow(anchorTabs.get(state.getName())); 
//		MNMain.getInstance().getStateManager().navigate(state, ""); 
	}
}

private void showTabContent(AppState state) {
	for(Element el : contentEls.values()) 
		DOMUtil.hide(el); 	
	DOMUtil.show(contentEls.get(state.getName())); 	
}
	
}

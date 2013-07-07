package org.sgx.madrenecesidad.client.state;

import org.sgx.jsutil.client.DOMUtil;
import org.sgx.jsutil.client.appstate.AppState;
import org.sgx.madrenecesidad.client.MNMain;
//import org.sgx.madrenecesidad.client.ui.EditorPanel;

import com.google.gwt.dom.client.Element;
/**
 * abstract AppState for this application (Using UIObject views). 
 * @author sg
 *
 */
public class MNAppState implements AppState {

//	AppStateAction action; 
	
	@Override
	public void perform(String params) {
//		StateHelper.perform(this, params); 
		
	}

	String name, description; 
//	UIObject view = null; 
//	Element containerEl = null; 

//	protected abstract UIObject buildNewView(); 

	public MNAppState(String name
//			, Element containerEl
//			,AppStateAction action
			) {
//		this.action=action; 
//		this.containerEl = containerEl;
		this.name = name;
	}
	
//	public UIObject getView() {
//		if(view==null)
//			view = buildNewView(); 
//		return view; 		
//	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void uninstall() {
//		StateHelper.uninstall(this); 
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return getName();
	}

	
//	public void setContainerEl(Element containerEl) {
//		this.containerEl = containerEl;
//	}

//	@Override
//	public void perform(String params) {
//		DOMUtil.clear(getContainerEl()); 
//		getContainerEl().appendChild(getView().getElement()); 
//	}
}

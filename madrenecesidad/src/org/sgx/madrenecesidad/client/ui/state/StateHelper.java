package org.sgx.madrenecesidad.client.ui.state;

import org.sgx.jsutil.client.DOMUtil;
import org.sgx.madrenecesidad.client.MNMain;

import com.google.gwt.dom.client.Element;

public class StateHelper {

	public static void perform(MNAppState state, String params) {
		DOMUtil.clear(getContainerEl()); 
	}
	
	//helper method only
	public static Element getContainerEl() {
		return MNMain.getInstance().getLayout().getEditorPanel().getElement();
	}

	public static void uninstall(MNAppState s) {
		DOMUtil.clear(getContainerEl()); 
	}
	
}

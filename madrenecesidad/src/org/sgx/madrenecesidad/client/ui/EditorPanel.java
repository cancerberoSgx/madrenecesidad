package org.sgx.madrenecesidad.client.ui;

import java.util.HashMap;
import java.util.Map;

import org.sgx.madrenecesidad.client.ui.state.EditorComponent;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.UIObject;
/**
 * the editor panel mantain several editors opened sing using tabs. It is an editor manager. 
 * @author sg
 *
 */
public class EditorPanel extends UIObject {

	private static EditorPanelUiBinder uiBinder = GWT.create(EditorPanelUiBinder.class);

	interface EditorPanelUiBinder extends UiBinder<Element, EditorPanel> {
	}
	
	@UiField Element headerEl, contentEl; 
	
	Map<String, EditorComponent> editors; 
//	Map<String, MNState> editors; 
	
	public EditorPanel() {
		setElement(uiBinder.createAndBindUi(this));
		editors = new HashMap<String, EditorComponent>(); 
	}

	public void addEditor(EditorComponent ed) {
		editors.put(ed.getName(), ed); 
		addTabMarkup(ed); 
	}
	public void setSelected(String ed) {
		if(editors.containsKey(ed)) {
//			editors.get(ed).
		}
	}
	private void addTabMarkup(EditorComponent ed) {
		if(!editors.containsKey(ed.getName())) {
		//TODO	
		}
	}
}

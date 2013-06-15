package org.sgx.madrenecesidad.client.ui.state;

import org.sgx.madrenecesidad.client.ui.editors.MNEditor;

import com.google.gwt.user.client.ui.UIObject;

public class EditorComponent {
	String name;
	UIObject view;
	MNEditor<? extends Object> editor;

	private EditorComponent(String name, UIObject view, MNEditor<? extends Object> editor) {
		super();
		this.name = name;
		this.view = view;
		this.editor = editor;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UIObject getView() {
		return view;
	}

	public void setView(UIObject view) {
		this.view = view;
	}

	public MNEditor<? extends Object> getEditor() {
		return editor;
	}

	public void setEditor(MNEditor<? extends Object> editor) {
		this.editor = editor;
	}
	
}

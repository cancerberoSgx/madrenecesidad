package org.sgx.madrenecesidad.client.app2;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.UIObject;

public class ActionPanel extends UIObject {

	private static ActionPanelUiBinder uiBinder = GWT.create(ActionPanelUiBinder.class);

	interface ActionPanelUiBinder extends UiBinder<Element, ActionPanel> {
	}

	public ActionPanel() {
		setElement(uiBinder.createAndBindUi(this));
	}

}

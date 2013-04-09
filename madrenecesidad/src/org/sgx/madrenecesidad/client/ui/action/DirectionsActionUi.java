package org.sgx.madrenecesidad.client.ui.action;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class DirectionsActionUi extends Composite {

	private static DirectionsActionUiUiBinder uiBinder = GWT.create(DirectionsActionUiUiBinder.class);

	interface DirectionsActionUiUiBinder extends UiBinder<Widget, DirectionsActionUi> {
	}

	@UiField
	Element getDirectionsButton, modal, modalBody;

	public DirectionsActionUi() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public Element getDirectionsButton() {
		return getDirectionsButton;
	}
	
	public Element getModal() {
		return modal;
	}
	public Element getModalBody() {
		return modalBody;
	}
}

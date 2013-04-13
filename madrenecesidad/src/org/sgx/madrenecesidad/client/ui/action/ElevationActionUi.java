package org.sgx.madrenecesidad.client.ui.action;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class ElevationActionUi extends Composite {

	private static ElevationActionUiUiBinder uiBinder = GWT.create(ElevationActionUiUiBinder.class);

	interface ElevationActionUiUiBinder extends UiBinder<Widget, ElevationActionUi> {
	}
	
	@UiField Element button; 

	public Element getButton() {
		return button;
	}
	public ElevationActionUi() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}

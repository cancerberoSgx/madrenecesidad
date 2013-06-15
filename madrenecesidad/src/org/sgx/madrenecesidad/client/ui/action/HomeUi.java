package org.sgx.madrenecesidad.client.ui.action;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.UIObject;

public class HomeUi extends UIObject {

	private static HomeUiUiBinder uiBinder = GWT.create(HomeUiUiBinder.class);

	interface HomeUiUiBinder extends UiBinder<Element, HomeUi> {
	}

	public HomeUi() {
		setElement(uiBinder.createAndBindUi(this));
	}

}

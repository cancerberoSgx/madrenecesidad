package org.sgx.madrenecesidad.client.app2;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.UIObject;

public class AppMainCss extends UIObject {

	private static AppMainCssUiBinder uiBinder = GWT.create(AppMainCssUiBinder.class);

	interface AppMainCssUiBinder extends UiBinder<Element, AppMainCss> {
	}

	public AppMainCss() {
		setElement(uiBinder.createAndBindUi(this));
	}

}

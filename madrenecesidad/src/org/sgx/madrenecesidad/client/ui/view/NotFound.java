package org.sgx.madrenecesidad.client.ui.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.UIObject;

public class NotFound extends UIObject {

	private static NotFoundUiBinder uiBinder = GWT.create(NotFoundUiBinder.class);

	interface NotFoundUiBinder extends UiBinder<Element, NotFound> {
	}

	public NotFound() {
		setElement(uiBinder.createAndBindUi(this));
	}

}

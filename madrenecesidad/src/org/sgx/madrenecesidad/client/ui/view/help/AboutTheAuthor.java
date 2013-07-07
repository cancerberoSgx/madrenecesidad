package org.sgx.madrenecesidad.client.ui.view.help;

import org.sgx.madrenecesidad.client.util.bootstrap.Bootstrap;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.UIObject;

public class AboutTheAuthor extends UIObject {

	private static AboutTheAuthorUiBinder uiBinder = GWT.create(AboutTheAuthorUiBinder.class);

	interface AboutTheAuthorUiBinder extends UiBinder<Element, AboutTheAuthor> {
	}
	@UiField Element modal;
	public AboutTheAuthor(Element parent) {
		setElement(uiBinder.createAndBindUi(this));
		parent.appendChild(getElement()); 
		Bootstrap.modal(getElement(), "show"); 
	}

}

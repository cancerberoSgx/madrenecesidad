package org.sgx.madrenecesidad.client.ui.util;

import org.sgx.jsutil.client.DOMUtil;
import org.sgx.madrenecesidad.client.util.bootstrap.Bootstrap;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.UIObject;

public class CollapseButton extends UIObject {

	private static CollapseButtonUiBinder uiBinder = GWT.create(CollapseButtonUiBinder.class);

	interface CollapseButtonUiBinder extends UiBinder<Element, CollapseButton> {
	}

	@UiField
	Element main;
	String labelShow, labelHide;
	Element target;
	boolean show;

	public CollapseButton(String labelShow, String labelHide, Element target_) {
		this.target = target_;
		this.labelShow = labelShow;
		this.labelHide = labelHide;
		setElement(uiBinder.createAndBindUi(this));

		DOMUtil.addClickHandler(main, new DOMUtil.EventHandler() {
			@Override
			public void onEvent(Event event) {
				setShow(!isShow()); // switch
			}
		});
		setShow(true);
	}

	public void setShow(boolean show) {
		this.show = show;
		if (!show) {
			target.getStyle().setProperty("display", "none");
			main.setInnerText(labelShow);
		} else {
			target.getStyle().setProperty("display", "block");
			main.setInnerText(labelHide);
		}
	}

	public boolean isShow() {
		return show;
	}

}

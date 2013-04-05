package org.sgx.madrenecesidad.client.app2;

import org.sgx.madrenecesidad.client.UserInfo;
import org.sgx.madrenecesidad.client.util.MNUtil;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.UIObject;

public class UserPanel extends UIObject {

	private static UserPanelUiBinder uiBinder = GWT.create(UserPanelUiBinder.class);

	interface UserPanelUiBinder extends UiBinder<Element, UserPanel> {
	}
	
	@UiField Element loggedInPanel, notLoggedInPanel, userName; 
	@UiField AnchorElement loginAnchor; 

	public UserPanel() {
		setElement(uiBinder.createAndBindUi(this));
		UserInfo user = MNUtil.getUser();
		MNUtil.fixUserUrls(user); 
		
		if (user != null && user.isLoggedIn()) {
			notLoggedInPanel.removeFromParent(); 
			userName.setInnerText(user.nickname()); 
		}
		
		else {
			loggedInPanel.removeFromParent(); 
			loginAnchor.setHref(user.loginUrl()); 
		}
	}

}

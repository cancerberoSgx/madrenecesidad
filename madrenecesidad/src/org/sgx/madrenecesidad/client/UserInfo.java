package org.sgx.madrenecesidad.client;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * this is the object overlaying javascript google user account
 * 
 * @author sg
 * 
 */
public class UserInfo extends JavaScriptObject {
	protected UserInfo() {
	}

	public native final boolean isLoggedIn() /*-{
		return this.nickname!=null;
	}-*/;
	/**
	 * 
	 * @return
	 */
	public native final String email() /*-{
		return this.email;
	}-*/;

	/**
	 * 
	 * @param val
	 * @return this - for setter chaining
	 */
	public native final UserInfo email(String val) /*-{
		this.email = val;
		return this;
	}-*/;

	/**
	 * 
	 * @return
	 */
	public native final String nickname() /*-{
		return this.nickname;
	}-*/;

	/**
	 * 
	 * @param val
	 * @return this - for setter chaining
	 */
	public native final UserInfo nickname(String val) /*-{
		this.nickname = val;
		return this;
	}-*/;

	/**
	 * 
	 * @return
	 */
	public native final String federatedIdentity() /*-{
		return this.federatedIdentity;
	}-*/;

	/**
	 * 
	 * @param val
	 * @return this - for setter chaining
	 */
	public native final UserInfo federatedIdentity(String val) /*-{
		this.federatedIdentity = val;
		return this;
	}-*/;

	/**
	 * 
	 * @return
	 */
	public native final String userId() /*-{
		return this.userId;
	}-*/;

	/**
	 * 
	 * @param val
	 * @return this - for setter chaining
	 */
	public native final UserInfo userId(String val) /*-{
		this.userId = val;
		return this;
	}-*/;

	/**
	 * 
	 * @return
	 */
	public native final String authDomain() /*-{
		return this.authDomain;
	}-*/;

	/**
	 * 
	 * @param val
	 * @return this - for setter chaining
	 */
	public native final UserInfo authDomain(String val) /*-{
		this.authDomain = val;
		return this;
	}-*/;

	/**
	 * 
	 * @return
	 */
	public native final String loginUrl() /*-{
		return this.loginUrl;
	}-*/;

	/**
	 * 
	 * @param val
	 * @return this - for setter chaining
	 */
	public native final UserInfo loginUrl(String val) /*-{
		this.loginUrl = val;
		return this;
	}-*/;

	/**
	 * 
	 * @return
	 */
	public native final String logoutUrl() /*-{
		return this.logoutUrl;
	}-*/;

	/**
	 * 
	 * @param val
	 * @return this - for setter chaining
	 */
	public native final UserInfo logoutUrl(String val) /*-{
		this.logoutUrl = val;
		return this;
	}-*/;
}

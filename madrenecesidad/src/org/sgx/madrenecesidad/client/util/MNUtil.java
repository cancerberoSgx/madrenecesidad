package org.sgx.madrenecesidad.client.util;

import org.sgx.madrenecesidad.client.MNConstants;
import org.sgx.madrenecesidad.client.UserInfo;

public class MNUtil {
	public static final native UserInfo getUser()/*-{
		return $wnd._webuser || null;
	}-*/;

	// public static String fixUrl(String url) {
	// if(MNConstants.develmode) {
	// return url+"&gwt.codesvr=127.0.0.1:9997";
	// }
	// else {
	// return url;
	// }
	// }

	public static long parseLong(String s, long def) {
		try {
			return Long.parseLong(s);
		} catch (Exception e) {
			return def;
		}
	}

	public static void fixUserUrls(UserInfo user) {
		if (MNConstants.develmode) {
			user.loginUrl(user.loginUrl() + org.sgx.jsutil.client.JsUtil.escape("?gwt.codesvr=127.0.0.1:9997"));// +"&gwt.codesvr=127.0.0.1:9997");
			user.logoutUrl(user.logoutUrl() + org.sgx.jsutil.client.JsUtil.escape("?gwt.codesvr=127.0.0.1:9997"));// +"gwt.codesvr=127.0.0.1:9997");
		}
	}

	public static String fixRouteUrl(String url) {
		// if(MNConstants.develmode) {
		// return "/index.jsp?gwt.codesvr=127.0.0.1:9997"+url;
		// }
		// else
		return url;
	}

	public final native static String getCurrentAddressUrl()/*-{
		return $wnd.document.location.href;
	}-*/;
}

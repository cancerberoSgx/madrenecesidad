package org.sgx.madrenecesidad.client.util;

import org.sgx.madrenecesidad.client.MNConstants;
import org.sgx.madrenecesidad.client.UserInfo;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.jsonp.client.JsonpRequestBuilder;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

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
	
	
//	public static class PlaceAddResult extends JavaScriptObject {
//		protected PlaceAddResult(){}
//		public native final String status() /*-{
//		return this["status"]; 
//		}-*/;
//		public native final MNUtil.PlaceAddResult status(String val) /*-{
//		this["status"] = val; 
//		return this; 
//		}-*/;
//	}
//	public static void placesAddTest() {
//		String apiKey = "AIzaSyAeX11OLg0zyBDlpYr2lqs4POPEjyWrooY"; 
//		String url = "https://maps.googleapis.com/maps/api/place/add/json?sensor=false&key="+apiKey; 		
//		new JsonpRequestBuilder()..requestObject(url, new AsyncCallback<PlaceAddResult>() {
//			@Override
//			public void onFailure(Throwable caught) {
//				Window.alert("fail: "+caught); 
//			}
//			@Override
//			public void onSuccess(PlaceAddResult result) {
//				Window.alert("success: "+result.status()); 
//			}
//		}); 
//	}
}

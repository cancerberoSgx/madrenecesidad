package org.sgx.madrenecesidad.client.util.bootstrap;

import com.google.gwt.dom.client.Element;

public class Bootstrap {
	public static final native void dropdown(String selector)/*-{
		$wnd.$(selector).dropdown();
	}-*/;

	public static final native void dropdown(Element e)/*-{
		$wnd.$(e).dropdown();
	}-*/;
}

package org.sgx.madrenecesidad.client.util;

import com.google.gwt.dom.client.Element;
/**
 * 
 * @author sg
 *
 */
public class BootStrapUtil {
	/**
	 * 
	 * @param ref - valid css selector like #el1 or .bar .nav, etc.  
	 */
	public static final native void scrollspy(String ref)/*-{
		$wnd.$(ref).scrollspy();
	}-*/;

	public static final native void scrollspy(Element e)/*-{
		$wnd.$(e).scrollspy();
	}-*/;
}

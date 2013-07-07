package org.sgx.madrenecesidad.client.lang;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ExternalTextResource;
import com.google.gwt.resources.client.TextResource;

public interface LangResources extends ClientBundle {
	public static LangResources instance = GWT.create(LangResources.class);

	@Source("es.js")
	ExternalTextResource es();
	
	
	/*
	 * NOTE: en is not external - it is always available - not optional - bootstrapped.
	 */
	@Source("en.js")
	TextResource en();
}

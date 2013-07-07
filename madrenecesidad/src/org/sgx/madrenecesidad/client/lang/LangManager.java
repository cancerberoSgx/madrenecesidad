package org.sgx.madrenecesidad.client.lang;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.sgx.gwtsizzle.client.Sizzle;
import org.sgx.jsutil.client.JsObject;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.dom.client.Element;
import com.google.gwt.resources.client.ExternalTextResource;
import com.google.gwt.resources.client.ResourceCallback;
import com.google.gwt.resources.client.ResourceException;
import com.google.gwt.resources.client.TextResource;
import com.google.gwt.user.client.Window;

public class LangManager {

	private static final String DEFAULT_LANG = "en";
	private static final String ATTRIBUTE_INNERHTML = "data-html";
	private static final String SELECTOR_INNERHTML = "["+ATTRIBUTE_INNERHTML+"]";

	public LangManager() {
		langs = new HashMap<String, Lang>();
		listeners = new LinkedList<LangListener>(); 

		loadLang(DEFAULT_LANG);

	}

	List<LangListener> listeners;

	public void addListener(LangListener l) {
		listeners.add(l);
	}

	Map<String, Lang> langs = new HashMap<String, Lang>();
	Lang currentLang;

	/**
	 * @param name
	 * @return null given lang is not found.
	 */
	public void loadLang(String name) {
		if (!langs.containsKey(name))
			createLang(name);
		else
			doInstallLang(langs.get(name)); 

		// if(langs.get(name)==null)
		// return null; //lang not found
	}

	
	private void updateDocument() {
		JsArray<Element> els = Sizzle.sizzleArray(SELECTOR_INNERHTML);
		for (int i = 0; i < els.length(); i++) {

			
			Element el = els.get(i);
			String key = el.getAttribute(ATTRIBUTE_INNERHTML);			
			String value = getCurrentLang().get(key); 
			if(value==null)
				value=getDefaultLang().get(key); 
			System.out.println("updateDocument: "+el+" - "+value+" - "+getCurrentLang().getName());
			
			el.setInnerHTML(value);
		}
	}

	private void doCreateLang(String name, String json) {
		JsObject data = JsonUtils.unsafeEval(json);
		Lang lang = new Lang(name);
		lang.setData(data);		
		langs.put(name, lang);		
		doInstallLang(lang); 		
	}

	private void doInstallLang(Lang lang) {
		currentLang=lang; 		
		updateDocument(); 		
		notifyAllLangLoaded(lang);
	}


	private void createLang(final String name) {
		if (name.equals(DEFAULT_LANG)) {
			String json = LangResources.instance.en().getText();
			doCreateLang(name, json);
		} else {
			ExternalTextResource res = getRes(name);
			try {
				res.getText(new ResourceCallback<TextResource>() {

					@Override
					public void onSuccess(TextResource resource) {
						String json = resource.getText();
						doCreateLang(name, json);
					}

					@Override
					public void onError(ResourceException e) {
						// TODO Auto-generated catch block
						Window.alert("Resource ERROR: " + e);
					}
				});
			} catch (ResourceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Window.alert("Resource ERROR: " + e);
			}
		}
	}

	private void notifyAllLangLoaded(Lang lang) {
		for (LangListener l : listeners) {
			l.loaded(lang);
		}
	}

	private ExternalTextResource getRes(String name) {

		if (name.equals("es"))
			return LangResources.instance.es();
		else
			return LangResources.instance.es();

	}

	public Lang getCurrentLang() {
		return currentLang;
	}
	public Lang getDefaultLang() {
		return langs.get(DEFAULT_LANG);
	}
//	public void setCurrentLang(Lang currentLang) {
//		this.currentLang = currentLang;
//	}

}

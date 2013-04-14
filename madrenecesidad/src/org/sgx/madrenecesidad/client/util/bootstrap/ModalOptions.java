package org.sgx.madrenecesidad.client.util.bootstrap;

import com.google.gwt.core.client.JavaScriptObject;
/**
 * options for modal
 * @see http://twitter.github.io/bootstrap/javascript.html#modals
 * @author sg
 *
 */
public class ModalOptions extends JavaScriptObject {
	protected ModalOptions() {
	}

	public static final native ModalOptions create()/*-{
		return {};
	}-*/;

	/**
	 * Includes a modal-backdrop element. Alternatively, specify static for a backdrop which doesn't close the modal on click.
	 * 
	 * @return
	 */
	public native final boolean backdrop() /*-{
		return this["backdrop"];
	}-*/;

	/**
	 * Includes a modal-backdrop element. Alternatively, specify static for a backdrop which doesn't close the modal on click.
	 * 
	 * @param val
	 * @return this - for setter chaining
	 */
	public native final ModalOptions backdrop(boolean val) /*-{
		this["backdrop"] = val;
		return this;
	}-*/;

	/**
	 * Closes the modal when escape key is pressed
	 * 
	 * @return
	 */
	public native final boolean keyboard() /*-{
		return this["keyboard"];
	}-*/;

	/**
	 * Closes the modal when escape key is pressed
	 * 
	 * @param val
	 * @return this - for setter chaining
	 */
	public native final ModalOptions keyboard(boolean val) /*-{
		this["keyboard"] = val;
		return this;
	}-*/;

	/**
	 * Shows the modal when initialized.
	 * 
	 * @return
	 */
	public native final boolean show() /*-{
		return this["show"];
	}-*/;

	/**
	 * Shows the modal when initialized.
	 * 
	 * @param val
	 * @return this - for setter chaining
	 */
	public native final ModalOptions show(boolean val) /*-{
		this["show"] = val;
		return this;
	}-*/;

	/**
	 * If a remote url is provided, content will be loaded via jQuery's load method and injected into the .modal-body. If you're using the data api, you may alternatively use the
	 * href tag to specify the remote source. An example of this is shown below:
	 * 
	 * <pre>
	 * <a data-toggle="modal" href="remote.html" data-target="#modal">click me</a>
	 * </pre>
	 * 
	 * @return
	 */
	public native final String remote() /*-{
		return this["remote"];
	}-*/;

	/**
	 * If a remote url is provided, content will be loaded via jQuery's load method and injected into the .modal-body. If you're using the data api, you may alternatively use the
	 * href tag to specify the remote source. An example of this is shown below:
	 * 
	 * <pre>
	 * <a data-toggle="modal" href="remote.html" data-target="#modal">click me</a>
	 * </pre>
	 * 
	 * @param val
	 * @return this - for setter chaining
	 */
	public native final ModalOptions remote(String val) /*-{
		this["remote"] = val;
		return this;
	}-*/;
}

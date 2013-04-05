package org.sgx.madrenecesidad.client.system;

public interface System {
	public boolean addListener(SystemListener e);
	/**
	 * this is different. add a listener that will be notified instantly if isLoaded()==true. if not
	 * the listener will be registered using addListener()
	 * @param e
	 * @return
	 */
	public boolean addListener2(SystemListener e); 
//	public void notifyAllChanged(); 
	boolean isLoaded(); 
}

package org.sgx.madrenecesidad.client.system;

import java.util.LinkedList;
import java.util.List;

public abstract class AbstractSystem implements System {

	List<SystemListener> listeners;
	boolean loaded; 
	
	public AbstractSystem(){
		listeners = new LinkedList<SystemListener>(); 
		loaded=false; 
	}
	
	void dispathRPCError(String string, Throwable caught) {
		//TODO
	}
	
	public boolean addListener(SystemListener e) {
		return listeners.add(e);
	}
	public boolean addListener2(SystemListener e) {
		boolean ret = listeners.add(e);
		if(isLoaded()) {
			e.changed();
		}
		return ret; 
	}
	
	public void notifyAllChanged() {
		loaded=true; 
		for(SystemListener l : listeners) {
			l.changed(); 
		}
	}
	

	public boolean isLoaded() {
		return loaded; 
	}
//	public void notifyAllLoaded() {
//		for(SystemListener l : listeners) {
//			l.loaded(); 
//		}
//	}
}

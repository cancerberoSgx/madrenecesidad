package org.sgx.gwtpersistor.client;

public interface Model {
	/**
	 * @return this entity id used for persistence (entity identification). 
	 * Must return null for those never saved entities (i.e about to be added for the first time)
	 * @return
	 */
String getPersistenceId();
}

package org.sgx.gwtpersistor.client;

import java.util.List;

public interface CollectionPersistor<T extends Model> {
	List<T> load(List<String> ids) throws PersistorException;
	void save(List<T> models) throws PersistorException;
}

package org.sgx.gwtpersistor.client;

public interface Persistor<T extends Model> {
PersistorResponse save(T model) throws PersistorException; 
PersistorResponse load(String id)throws PersistorException; 
}

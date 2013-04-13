package org.sgx.madrenecesidad.client.ui.editors;

public interface MNEditor<T> {
T flush();
void load(T t);
}

package org.sgx.madrenecesidad.client.ui.editors.search;

public interface ColumnPrinter<T extends Searchable> {
	String getHTML(T model);
//	String getColumnnName(); 
//	String getTypeName();
}
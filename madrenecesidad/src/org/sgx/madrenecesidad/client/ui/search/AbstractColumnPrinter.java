package org.sgx.madrenecesidad.client.ui.search;

public abstract class  AbstractColumnPrinter implements ColumnPrinter {
String columnName, typeName;

public AbstractColumnPrinter(String columnName, String typeName) {
	super();
	this.columnName = columnName;
	this.typeName = typeName;
} 

}

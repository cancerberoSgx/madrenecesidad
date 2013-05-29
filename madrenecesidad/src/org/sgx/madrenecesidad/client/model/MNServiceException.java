package org.sgx.madrenecesidad.client.model;

import java.io.Serializable;

public class MNServiceException extends Exception implements Serializable {

	public static final String MESSAGE_PERMISSION="You don't have permission for performing the action"; 
	private static final long serialVersionUID = 1L;

	public MNServiceException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MNServiceException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public MNServiceException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public MNServiceException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
        String s = "Service Error: ";//getClass().getName();
        String message = getLocalizedMessage();
        return (message != null) ? (s + ": " + message) : s;
	}
}

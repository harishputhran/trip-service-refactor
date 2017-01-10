package org.craftedsw.tripservicekata.exception;

public class UserDetailsUnavailableException extends RuntimeException {

	private static final long serialVersionUID = 3597601671227544905L;
	
	public UserDetailsUnavailableException() {
		super();
	}

	public UserDetailsUnavailableException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserDetailsUnavailableException(String message) {
		super(message);
	}
}

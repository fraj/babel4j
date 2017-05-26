package io.github.fraj.babel4j.resolvers;

public class UnresolvableException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public UnresolvableException(String argMessage) {
		super(argMessage);
	}

	public UnresolvableException(String argMessage, Throwable argCause) {
		super(argMessage, argCause);
	}

}

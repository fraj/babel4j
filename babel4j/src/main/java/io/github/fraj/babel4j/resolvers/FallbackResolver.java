package io.github.fraj.babel4j.resolvers;

import java.util.Arrays;

public class FallbackResolver implements Resolver {
	
	private static final String UNRESOLVED_MESSAGE_TEMPLATE = "Unresolved message (key=%s, params=%s)";
	
	private static final FallbackResolver SINGLETON = new FallbackResolver();
	
	private FallbackResolver() {
		
	}
	
	public static FallbackResolver instance() {
		return SINGLETON;
	}
	
	@Override
	public String resolve(String argKey, Object... argParams) {
		return String.format(UNRESOLVED_MESSAGE_TEMPLATE, argKey, Arrays.toString(argParams));
	}

}

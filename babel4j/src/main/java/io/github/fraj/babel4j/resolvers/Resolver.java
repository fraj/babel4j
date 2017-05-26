package io.github.fraj.babel4j.resolvers;

public interface Resolver {
	
	String resolve(String argKey, Object... argParams);

}

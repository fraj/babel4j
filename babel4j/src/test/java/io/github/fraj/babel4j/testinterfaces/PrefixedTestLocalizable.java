package io.github.fraj.babel4j.testinterfaces;

import io.github.fraj.babel4j.annotations.Prefix;

@Prefix("example")
public interface PrefixedTestLocalizable {
	
	String simpleMessage();
	String singleParameterMessage(String argParam);
	String dualParameterMessage(String argParam1, String argParam2);

}

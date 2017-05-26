package io.github.fraj.babel4j.resolvers;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

public class MapResolver extends FormattingResolver {
	
	private final Map<String, String> map;
	
	public MapResolver(Map<String, String> argMap, Formatter argFormatter) {
		super(argFormatter);
		Objects.requireNonNull(argMap);
		map = Collections.unmodifiableMap(argMap);
	}
	
	@Override
	protected String getTemplate(String argKey, Object... argParams) {
		return map.get(argKey);
	}
	
}

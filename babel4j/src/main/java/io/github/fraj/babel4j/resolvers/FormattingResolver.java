package io.github.fraj.babel4j.resolvers;

import java.util.Objects;

public abstract class FormattingResolver implements Resolver {
	
	private final Formatter formatter;
	
	public FormattingResolver(Formatter argFormatter) {
		Objects.requireNonNull(argFormatter);
		formatter = argFormatter;
	}
	
	@Override
	public final String resolve(String argKey, Object... argParams) {
		String locTemplate = getTemplate(argKey, argParams);
		if (locTemplate == null) {
			throw new UnresolvableException("Could not find the message template");
		}
		return formatter.format(locTemplate, argParams);
	}
	
	protected abstract String getTemplate(String argKey, Object... argParams);

}

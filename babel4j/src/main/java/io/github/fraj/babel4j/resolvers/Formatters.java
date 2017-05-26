package io.github.fraj.babel4j.resolvers;

import java.text.MessageFormat;
import java.util.MissingFormatArgumentException;
import java.util.Objects;

public enum Formatters implements Formatter {
	
	PRINTF {
		@Override
		public String format(String argTemplate, Object... argParams) {
			Objects.requireNonNull(argTemplate);
			try {
				return String.format(argTemplate, argParams);
			} catch (MissingFormatArgumentException e) {
				throw new UnresolvableException(EXCEPTION_MESSAGE_INVALID_PARAMS, e);
			}
		}
	},
	MESSAGE_FORMAT {
		@Override
		public String format(String argTemplate, Object... argParams) {
			Objects.requireNonNull(argTemplate);
			MessageFormat locFormat = new MessageFormat(argTemplate);
			if (locFormat.getFormatsByArgumentIndex().length > argParams.length) {
				throw new UnresolvableException(EXCEPTION_MESSAGE_INVALID_PARAMS);
			}
			return locFormat.format(argParams);
		}
	};

	private static final String EXCEPTION_MESSAGE_INVALID_PARAMS = "Invalid number of parameters";
	
}

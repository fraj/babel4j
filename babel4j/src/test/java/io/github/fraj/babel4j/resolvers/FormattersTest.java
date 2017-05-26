package io.github.fraj.babel4j.resolvers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;

import java.text.MessageFormat;
import java.util.MissingFormatArgumentException;

import org.junit.Test;

public class FormattersTest {

	private static final String EXCEPTION_MESSAGE_INVALID_PARAMS = "Invalid number of parameters";

	private static final String SIMPLE_MESSAGE = "This is a simple message";
	private static final String SINGLE_PARAM_MESSAGE_PRINTF = "This is a message with param %s";
	private static final String TWO_PARAMS_MESSAGE_PRINTF = "This is a message with params %s and %s";
	private static final String SINGLE_PARAM_MESSAGE_MF = "This is a message with param {0}";
	private static final String TWO_PARAMS_MESSAGE_MF = "This is a message with params {0} and {1}";

	@Test
	public void testFormat_Printf_SimpleMessageNoArgs() throws Exception {
		assertThat(Formatters.PRINTF.format(SIMPLE_MESSAGE)).isEqualTo(SIMPLE_MESSAGE);
	}

	@Test
	public void testFormat_Printf_SimpleMessageWith1Arg() throws Exception {
		assertThat(Formatters.PRINTF.format(SIMPLE_MESSAGE, "toto")).isEqualTo(SIMPLE_MESSAGE);
	}

	@Test
	public void testFormat_Printf_SingleParamMessageNoArgs() throws Exception {
		try {
			Formatters.PRINTF.format(SINGLE_PARAM_MESSAGE_PRINTF);
			failBecauseExceptionWasNotThrown(UnresolvableException.class);
		} catch (UnresolvableException e) {
			assertThat(e).hasMessage(EXCEPTION_MESSAGE_INVALID_PARAMS);
			assertThat(e).hasCauseInstanceOf(MissingFormatArgumentException.class);
		}
	}

	@Test
	public void testFormat_Printf_SingleParamMessageWith1Arg() throws Exception {
		assertThat(Formatters.PRINTF.format(SINGLE_PARAM_MESSAGE_PRINTF, "toto"))
				.isEqualTo(String.format(SINGLE_PARAM_MESSAGE_PRINTF, "toto"));
	}

	@Test
	public void testFormat_Printf_SingleParamMessageWith2Args() throws Exception {
		assertThat(Formatters.PRINTF.format(SINGLE_PARAM_MESSAGE_PRINTF, "toto", "titi"))
				.isEqualTo(String.format(SINGLE_PARAM_MESSAGE_PRINTF, "toto"));
	}

	@Test
	public void testFormat_Printf_TwoParamsMessageNoArgs() throws Exception {
		try {
			Formatters.PRINTF.format(TWO_PARAMS_MESSAGE_PRINTF);
			failBecauseExceptionWasNotThrown(UnresolvableException.class);
		} catch (UnresolvableException e) {
			assertThat(e).hasMessage(EXCEPTION_MESSAGE_INVALID_PARAMS);
			assertThat(e).hasCauseInstanceOf(MissingFormatArgumentException.class);
		}
	}

	@Test
	public void testFormat_Printf_TwoParamsMessageWith1Arg() throws Exception {
		try {
			Formatters.PRINTF.format(TWO_PARAMS_MESSAGE_PRINTF, "toto");
			failBecauseExceptionWasNotThrown(UnresolvableException.class);
		} catch (UnresolvableException e) {
			assertThat(e).hasMessage(EXCEPTION_MESSAGE_INVALID_PARAMS);
			assertThat(e).hasCauseInstanceOf(MissingFormatArgumentException.class);
		}
	}

	@Test
	public void testFormat_Printf_TwoParamsMessageWith2Args() throws Exception {
		assertThat(Formatters.PRINTF.format(TWO_PARAMS_MESSAGE_PRINTF, "toto", "titi"))
				.isEqualTo(String.format(TWO_PARAMS_MESSAGE_PRINTF, "toto", "titi"));
	}

	@Test
	public void testFormat_Printf_TwoParamsMessageWith3Args() throws Exception {
		assertThat(Formatters.PRINTF.format(TWO_PARAMS_MESSAGE_PRINTF, "toto", "titi", "tutu"))
				.isEqualTo(String.format(TWO_PARAMS_MESSAGE_PRINTF, "toto", "titi"));
	}

	@Test
	public void testFormat_Printf_Null() throws Exception {
		try {
			Formatters.PRINTF.format(null);
			failBecauseExceptionWasNotThrown(NullPointerException.class);
		} catch (NullPointerException e) {
			// No code
		}
	}

	@Test
	public void testFormat_MessageFormat_SimpleMessageNoArgs() throws Exception {
		assertThat(Formatters.MESSAGE_FORMAT.format(SIMPLE_MESSAGE)).isEqualTo(SIMPLE_MESSAGE);
	}

	@Test
	public void testFormat_MessageFormat_SimpleMessageWith1Arg() throws Exception {
		assertThat(Formatters.MESSAGE_FORMAT.format(SIMPLE_MESSAGE, "toto")).isEqualTo(SIMPLE_MESSAGE);
	}

	@Test
	public void testFormat_MessageFormat_SingleParamMessageNoArgs() throws Exception {
		try {
			Formatters.MESSAGE_FORMAT.format(SINGLE_PARAM_MESSAGE_MF);
			failBecauseExceptionWasNotThrown(UnresolvableException.class);
		} catch (UnresolvableException e) {
			assertThat(e).hasMessage(EXCEPTION_MESSAGE_INVALID_PARAMS);
			assertThat(e).hasNoCause();
		}
	}

	@Test
	public void testFormat_MessageFormat_SingleParamMessageWith1Arg() throws Exception {
		assertThat(Formatters.MESSAGE_FORMAT.format(SINGLE_PARAM_MESSAGE_MF, "toto"))
				.isEqualTo(MessageFormat.format(SINGLE_PARAM_MESSAGE_MF, "toto"));
	}

	@Test
	public void testFormat_MessageFormat_SingleParamMessageWith2Args() throws Exception {
		assertThat(Formatters.MESSAGE_FORMAT.format(SINGLE_PARAM_MESSAGE_MF, "toto", "titi"))
				.isEqualTo(MessageFormat.format(SINGLE_PARAM_MESSAGE_MF, "toto"));
	}

	@Test
	public void testFormat_MessageFormat_TwoParamsMessageNoArgs() throws Exception {
		try {
			Formatters.MESSAGE_FORMAT.format(TWO_PARAMS_MESSAGE_MF);
			failBecauseExceptionWasNotThrown(UnresolvableException.class);
		} catch (UnresolvableException e) {
			assertThat(e).hasMessage(EXCEPTION_MESSAGE_INVALID_PARAMS);
			assertThat(e).hasNoCause();
		}
	}

	@Test
	public void testFormat_MessageFormat_TwoParamsMessageWith1Arg() throws Exception {
		try {
			Formatters.MESSAGE_FORMAT.format(TWO_PARAMS_MESSAGE_MF, "toto");
			failBecauseExceptionWasNotThrown(UnresolvableException.class);
		} catch (UnresolvableException e) {
			assertThat(e).hasMessage(EXCEPTION_MESSAGE_INVALID_PARAMS);
			assertThat(e).hasNoCause();
		}
	}

	@Test
	public void testFormat_MessageFormat_TwoParamsMessageWith2Args() throws Exception {
		assertThat(Formatters.MESSAGE_FORMAT.format(TWO_PARAMS_MESSAGE_MF, "toto", "titi"))
				.isEqualTo(MessageFormat.format(TWO_PARAMS_MESSAGE_MF, "toto", "titi"));
	}

	@Test
	public void testFormat_MessageFormat_TwoParamsMessageWith3Args() throws Exception {
		assertThat(Formatters.MESSAGE_FORMAT.format(TWO_PARAMS_MESSAGE_MF, "toto", "titi", "tutu"))
				.isEqualTo(MessageFormat.format(TWO_PARAMS_MESSAGE_MF, "toto", "titi"));
	}

	@Test
	public void testFormat_MessageFormat_Null() throws Exception {
		try {
			Formatters.MESSAGE_FORMAT.format(null);
			failBecauseExceptionWasNotThrown(NullPointerException.class);
		} catch (NullPointerException e) {
			// No code
		}
	}

}
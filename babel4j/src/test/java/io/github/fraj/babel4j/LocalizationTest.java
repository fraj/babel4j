package io.github.fraj.babel4j;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import io.github.fraj.babel4j.Localization;
import io.github.fraj.babel4j.resolvers.Resolver;
import io.github.fraj.babel4j.testinterfaces.PrefixedTestLocalizable;
import io.github.fraj.babel4j.testinterfaces.SimpleTestLocalizable;

public class LocalizationTest {
	
	private final Resolver resolverMock;

	public LocalizationTest() {
		resolverMock = mock(Resolver.class);
		when(resolverMock.resolve(anyString(), any())).then(new Answer<String>() {
			@Override
			public String answer(InvocationOnMock argInvocation) throws Throwable {
				return Arrays.toString(argInvocation.getArguments());
			}
		});
	}
	
	@Test
	public void testCreate_Nominal() throws Exception {
		SimpleTestLocalizable locLocalizable = Localization.of(SimpleTestLocalizable.class);
		assertThat(locLocalizable).isNotNull();
	}

	@Test
	public void testCreate_Prefixed() throws Exception {
		PrefixedTestLocalizable locLocalizable = Localization.of(PrefixedTestLocalizable.class);
		assertThat(locLocalizable).isNotNull();
	}

	@Test
	public void testCreate_WithResolver() throws Exception {
		SimpleTestLocalizable locLocalizable = Localization.resolvedBy(resolverMock)
				.of(SimpleTestLocalizable.class);
		assertThat(locLocalizable).isNotNull();
	}

	@Test
	public void testCreate_WithNullResolver() throws Exception {
		try {
			Localization.resolvedBy(null).of(SimpleTestLocalizable.class);
			failBecauseExceptionWasNotThrown(NullPointerException.class);
		} catch (NullPointerException e) {
		}
	}

	@Test
	public void testSimpleMessage_Nominal() throws Exception {
		SimpleTestLocalizable locLocalizable = Localization.of(SimpleTestLocalizable.class);
		assertThat(locLocalizable.simpleMessage()).isEqualTo(
				"Unresolved message (key=io.github.fraj.babel4j.testinterfaces.SimpleTestLocalizable.simpleMessage, params=[])");
	}

	@Test
	public void testSimpleMessage_Prefixed() throws Exception {
		PrefixedTestLocalizable locLocalizable = Localization.of(PrefixedTestLocalizable.class);
		assertThat(locLocalizable.simpleMessage())
				.isEqualTo("Unresolved message (key=example.simpleMessage, params=[])");
	}

	@Test
	public void testSimpleMessage_WithResolver() throws Exception {
		SimpleTestLocalizable locLocalizable = Localization.resolvedBy(resolverMock)
				.of(SimpleTestLocalizable.class);
		assertThat(locLocalizable.simpleMessage())
				.isEqualTo("[io.github.fraj.babel4j.testinterfaces.SimpleTestLocalizable.simpleMessage]");
	}

	@Test
	public void testSingleParamMessage_Nominal() throws Exception {
		SimpleTestLocalizable locLocalizable = Localization.of(SimpleTestLocalizable.class);
		assertThat(locLocalizable.singleParameterMessage("toto")).isEqualTo(
				"Unresolved message (key=io.github.fraj.babel4j.testinterfaces.SimpleTestLocalizable.singleParameterMessage, params=[toto])");
	}

	@Test
	public void testSingleParamMessage_Prefixed() throws Exception {
		PrefixedTestLocalizable locLocalizable = Localization.of(PrefixedTestLocalizable.class);
		assertThat(locLocalizable.singleParameterMessage("toto"))
				.isEqualTo("Unresolved message (key=example.singleParameterMessage, params=[toto])");
	}

	@Test
	public void testDualParamMessage_Nominal() throws Exception {
		SimpleTestLocalizable locLocalizable = Localization.of(SimpleTestLocalizable.class);
		assertThat(locLocalizable.dualParameterMessage("toto", "titi")).isEqualTo(
				"Unresolved message (key=io.github.fraj.babel4j.testinterfaces.SimpleTestLocalizable.dualParameterMessage, params=[toto, titi])");
	}

	@Test
	public void testDualParamMessage_Prefixed() throws Exception {
		PrefixedTestLocalizable locLocalizable = Localization.of(PrefixedTestLocalizable.class);
		assertThat(locLocalizable.dualParameterMessage("toto", "titi"))
				.isEqualTo("Unresolved message (key=example.dualParameterMessage, params=[toto, titi])");
	}

	@Test
	public void testDualParamMessage_WithResolver() throws Exception {
		SimpleTestLocalizable locLocalizable = Localization.resolvedBy(resolverMock)
				.of(SimpleTestLocalizable.class);
		assertThat(locLocalizable.dualParameterMessage("toto", "titi"))
				.isEqualTo("[io.github.fraj.babel4j.testinterfaces.SimpleTestLocalizable.dualParameterMessage, toto, titi]");
	}

	@Test
	public void testCreate_NullArg() throws Exception {
		try {
			Localization.of(null);
			failBecauseExceptionWasNotThrown(NullPointerException.class);
		} catch (NullPointerException e) {
		}
	}

}

package io.github.fraj.babel4j.resolvers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import io.github.fraj.babel4j.resolvers.MapResolver;

public class MapResolverTest {
	
	private static final String SIMPLE_MESSAGE_KEY = "message.simple";
	private static final String SIMPLE_MESSAGE_VALUE = "This is a simple message";
	private static final String SINGLE_PARAM_MESSAGE_KEY = "message.singleParam";
	private static final String SINGLE_PARAM_MESSAGE_VALUE = "This is a message with param %s";
	private static final String INVALID_KEY = "fake";
	
	private final MapResolver tested;
	private final Formatter mockFormatter;
	
	public MapResolverTest() {
		Map<String, String> locMap = new HashMap<>();
		locMap.put(SIMPLE_MESSAGE_KEY, SIMPLE_MESSAGE_VALUE);
		locMap.put(SINGLE_PARAM_MESSAGE_KEY, SINGLE_PARAM_MESSAGE_VALUE);
		mockFormatter = mock(Formatter.class);
		when(mockFormatter.format(anyString(), any())).thenReturn("mockResult");
		when(mockFormatter.format(eq(SINGLE_PARAM_MESSAGE_VALUE))).thenThrow(new UnresolvableException("mock message"));
		tested = new MapResolver(locMap, mockFormatter);
	}
	
	@Test
	public void testResolve_SimpleMessageNoArgs() throws Exception {
		assertThat(tested.resolve(SIMPLE_MESSAGE_KEY)).isEqualTo("mockResult");
		verify(mockFormatter, times(1)).format(SIMPLE_MESSAGE_VALUE);
		verifyNoMoreInteractions(mockFormatter);
	}

	@Test
	public void testResolve_SimpleMessageWith2Args() throws Exception {
		assertThat(tested.resolve(SIMPLE_MESSAGE_KEY, "toto", "titi")).isEqualTo("mockResult");
		verify(mockFormatter, times(1)).format(SIMPLE_MESSAGE_VALUE, "toto", "titi");
		verifyNoMoreInteractions(mockFormatter);
	}

	@Test
	public void testResolve_SingleParamMessageNoArgs() throws Exception {
		try {
			tested.resolve(SINGLE_PARAM_MESSAGE_KEY);
			failBecauseExceptionWasNotThrown(UnresolvableException.class);
		} catch (UnresolvableException e) {
			assertThat(e).hasMessage("mock message");
			assertThat(e).hasNoCause();
		}
	}

	@Test
	public void testResolve_SingleParamMessageWith2Args() throws Exception {
		assertThat(tested.resolve(SINGLE_PARAM_MESSAGE_KEY, "toto", "titi")).isEqualTo("mockResult");
		verify(mockFormatter, times(1)).format(SINGLE_PARAM_MESSAGE_VALUE, "toto", "titi");
		verifyNoMoreInteractions(mockFormatter);
	}

	@Test
	public void testResolve_InvalidKeyNoArgs() throws Exception {
		try {
			tested.resolve(INVALID_KEY);
			failBecauseExceptionWasNotThrown(UnresolvableException.class);
		} catch (UnresolvableException e) {
			assertThat(e).hasMessage("Could not find the message template");
			assertThat(e).hasNoCause();
		}
	}

}

package io.github.fraj.babel4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Objects;

import io.github.fraj.babel4j.annotations.Prefix;
import io.github.fraj.babel4j.resolvers.FallbackResolver;
import io.github.fraj.babel4j.resolvers.Resolver;

public class Localization {

	private static final Resolver FALLBACK_RESOLVER = FallbackResolver.instance();

	private Localization() {

	}

	public static Builder resolvedBy(Resolver argResolver) {
		Objects.requireNonNull(argResolver);
		return new Builder(argResolver);
	}
	
	public static <T> T of(Class<T> argInterface) {
		return resolvedBy(FALLBACK_RESOLVER).of(argInterface);
	}

	private static class InvocationHandlerImpl implements InvocationHandler {

		private final String prefix;
		private final Resolver resolver;

		public InvocationHandlerImpl(String argPrefix, Resolver argResolver) {
			prefix = argPrefix;
			resolver = argResolver;
		}

		@Override
		public Object invoke(Object argProxy, Method argMethod, Object[] args) throws Throwable {
			if (args == null) {
				return resolver.resolve(prefix + "." + argMethod.getName());
			}
			return resolver.resolve(prefix + "." + argMethod.getName(), args);
		}

	}

	public static class Builder {

		private Resolver resolver;

		private Builder(Resolver argResolver) {
			resolver = argResolver;
		}
		
		public <T> T of(Class<T> argInterface) {
			Objects.requireNonNull(argInterface);
			return (T) Proxy.newProxyInstance(argInterface.getClassLoader(), new Class<?>[] { argInterface },
					new InvocationHandlerImpl(getPrefix(argInterface), resolver));
		}
		
		private static String getPrefix(Class<?> argType) {
			Prefix locPrefixAnnotation = argType.getAnnotation(Prefix.class);
			if (locPrefixAnnotation == null) {
				return argType.getName();
			} else {
				return locPrefixAnnotation.value();
			}
		}

	}

}

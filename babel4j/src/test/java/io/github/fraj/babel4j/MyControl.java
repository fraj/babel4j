package io.github.fraj.babel4j;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Properties;
import java.util.ResourceBundle;

public class MyControl extends ResourceBundle.Control {
	
	private static final List<String> FORMATS = Collections.unmodifiableList(Arrays.asList("myformat"));
	
	@Override
	public List<String> getFormats(String baseName) {
		Objects.requireNonNull(baseName);
		return FORMATS;
	}
	
	@Override
	public ResourceBundle newBundle(String baseName, Locale locale, String format, ClassLoader loader, boolean reload)
			throws IllegalAccessException, InstantiationException, IOException {
		String locBundleName = toBundleName(baseName, locale);
		String locResourceName = toResourceName(locBundleName, "properties");
		List<URL> locUrls = Collections.list(loader.getResources(locResourceName));
		System.out.println("URLs for " + locBundleName + ": " + locUrls);
		Properties locProperties = new Properties();
		for (URL locUrl : locUrls) {
			URLConnection locConnection = locUrl.openConnection();
			InputStream locStream = locConnection.getInputStream();
			try {
				locProperties.load(locStream);
			} finally {
				locStream.close();
			}
		}
		System.out.println("Properties : " + locProperties);
		return null;
	}
	
	@Override
	public Locale getFallbackLocale(String baseName, Locale locale) {
		return null;
	}

}

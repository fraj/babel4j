package io.github.fraj.babel4j;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.ResourceBundle.Control;

import org.junit.Ignore;
import org.junit.Test;

public class AdvanceResourceTest {
	
	@Test
	@Ignore
	public void test_Nominal() throws Exception {
		Control locControl = new MyControl();
		ResourceBundle.getBundle("i18n.test2", Locale.FRANCE, locControl);
	}

}

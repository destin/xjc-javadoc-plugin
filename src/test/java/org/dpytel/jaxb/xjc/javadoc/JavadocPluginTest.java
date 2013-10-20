/**
 * 
 */
package org.dpytel.jaxb.xjc.javadoc;

import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.io.File;

import org.junit.Test;

import com.sun.tools.xjc.BadCommandLineException;
import com.sun.tools.xjc.Driver;

/**
 * @author Dawid Pytel
 * 
 */
public class JavadocPluginTest {

	@Test
	public void pluginShouldBeLoaded() throws Exception {
		try {
			Driver.run(new String[] {}, System.out, System.out);
			fail("Expected xjc to fail with BadCommandLineException");
		} catch (BadCommandLineException e) {
			assertThat(e.getOptions().getAllPlugins(), hasItem(any(JavadocPlugin.class)));
		}
	}
	
	@Test
	public void testComplexTypeWithDocumentedProperties() throws Exception {
		String fileName = "complexTypeWithDocumentedProperties.xsd";
		assertProcessedSuccessful(fileName);
	}
	
	@Test
	public void testComplexTypeWithoutProperties() throws Exception {
		String fileName = "complexTypeWithoutProperties.xsd";
		assertProcessedSuccessful(fileName);
	}

	private void assertProcessedSuccessful(String fileName) throws Exception {
		String xsdPath = new File("src/test/resources", fileName).getAbsolutePath();
		String outputDir = "target";
		int result = Driver.run(new String[] {xsdPath , "-Xjavadoc", "-d", outputDir }, System.out, System.out);
		
		assertThat(result, is(0));
	}
}

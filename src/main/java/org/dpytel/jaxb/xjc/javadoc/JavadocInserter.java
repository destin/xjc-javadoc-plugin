/**
 * 
 */
package org.dpytel.jaxb.xjc.javadoc;

import java.util.Collection;

import org.xml.sax.ErrorHandler;

import com.sun.tools.xjc.Options;
import com.sun.tools.xjc.outline.ClassOutline;
import com.sun.tools.xjc.outline.EnumOutline;
import com.sun.tools.xjc.outline.FieldOutline;
import com.sun.tools.xjc.outline.Outline;
import com.sun.xml.xsom.XSComponent;

/**
 * @author Dawid Pytel
 * 
 */
public class JavadocInserter {

	private Outline outline;
	private Options options;

	public JavadocInserter(Outline outline, Options opt,
			ErrorHandler errorHandler) {
		this.outline = outline;
		this.options = opt;
	}

	/**
	 * @return true if successful
	 */
	public boolean addJavadocs() {
		addJavadocsToClasses();
		addJavadocsToEnums();
		return false;
	}

	private void addJavadocsToClasses() {
		for (ClassOutline classOutline : outline.getClasses()) {
			addJavadocs(classOutline);
		}
	}

	private void addJavadocs(ClassOutline classOutline) {
		FieldOutline[] declaredFields = classOutline.getDeclaredFields();
		for (FieldOutline fieldOutline : declaredFields) {
			PropertyJavadoc propertyJavadoc = new PropertyJavadoc(
					outline.getCodeModel(), options, classOutline, fieldOutline);
			propertyJavadoc.addJavadocs();
		}
	}

	private void addJavadocsToEnums() {
		Collection<EnumOutline> enums = outline.getEnums();
		for (EnumOutline enumOutline : enums) {
			addJavadoc(enumOutline);
		}
	}

	private void addJavadoc(EnumOutline enumOutline) {
		XSComponent schemaComponent = enumOutline.target.getSchemaComponent();
		String documentation = XSComponentHelper
				.getDocumentation(schemaComponent);
		if (documentation == null || "".equals(documentation)) {
			return;
		}
		enumOutline.clazz.javadoc().add(0, documentation + "\n\n");
	}

}

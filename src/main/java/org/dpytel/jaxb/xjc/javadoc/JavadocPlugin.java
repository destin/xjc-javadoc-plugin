/**
 * 
 */
package org.dpytel.jaxb.xjc.javadoc;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

import com.sun.codemodel.JFieldVar;
import com.sun.tools.xjc.Options;
import com.sun.tools.xjc.Plugin;
import com.sun.tools.xjc.model.CPropertyInfo;
import com.sun.tools.xjc.outline.ClassOutline;
import com.sun.tools.xjc.outline.FieldOutline;
import com.sun.tools.xjc.outline.Outline;
import com.sun.tools.xjc.reader.xmlschema.bindinfo.BindInfo;
import com.sun.xml.bind.v2.schemagen.xmlschema.Particle;
import com.sun.xml.xsom.XSAnnotation;
import com.sun.xml.xsom.XSComponent;
import com.sun.xml.xsom.XSParticle;
import com.sun.xml.xsom.XSTerm;

/**
 * Generates Javadocs based on xsd:documentation.
 * 
 * @author Dawid Pytel
 * 
 */
public class JavadocPlugin extends Plugin {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sun.tools.xjc.Plugin#getOptionName()
	 */
	@Override
	public String getOptionName() {
		return "Xjavadoc";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sun.tools.xjc.Plugin#getUsage()
	 */
	@Override
	public String getUsage() {
		return "  -Xjavadoc            :  Generates Javadocs based on xsd:documentation.";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sun.tools.xjc.Plugin#run(com.sun.tools.xjc.outline.Outline,
	 * com.sun.tools.xjc.Options, org.xml.sax.ErrorHandler)
	 */
	@Override
	public boolean run(Outline outline, Options opt, ErrorHandler errorHandler)
			throws SAXException {
		for (ClassOutline classOutline : outline.getClasses()) {
			addJavadocs(classOutline);
		}
		return false;
	}

	private void addJavadocs(ClassOutline classOutline) {
		FieldOutline[] declaredFields = classOutline.getDeclaredFields();
		for (FieldOutline fieldOutline : declaredFields) {
			addJavadocs(classOutline, fieldOutline);
		}
	}

	private void addJavadocs(ClassOutline classOutline,
			FieldOutline fieldOutline) {
		CPropertyInfo propertyInfo = fieldOutline.getPropertyInfo();
		if (propertyInfo == null) {
			return;
		}
		XSComponent schemaComponent = propertyInfo.getSchemaComponent();
		if (schemaComponent == null || !(schemaComponent instanceof XSParticle)) {
			return;
		}
		XSTerm term = ((XSParticle)schemaComponent).getTerm();
		if (term == null) {
			return;
		}
		XSAnnotation xsAnnotation = term.getAnnotation();
		if (xsAnnotation == null) {
			return;
		}
		BindInfo annotation = (BindInfo) xsAnnotation.getAnnotation();
		if (annotation == null) {
			return;
		}
		String documentation = annotation.getDocumentation();
		if (documentation == null) {
			return;
		}
		setJavadoc(classOutline, fieldOutline, documentation);
	}

	private void setJavadoc(ClassOutline classOutline,
			FieldOutline fieldOutline, String documentation) {
		JFieldVar fieldVar = classOutline.implClass.fields().get(
				fieldOutline.getPropertyInfo().getName(false));
		if (fieldVar == null) {
			return;
		}
		fieldVar.javadoc().append(documentation.trim());
	}

}

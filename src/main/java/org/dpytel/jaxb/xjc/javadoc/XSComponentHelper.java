package org.dpytel.jaxb.xjc.javadoc;

import com.sun.tools.xjc.reader.xmlschema.bindinfo.BindInfo;
import com.sun.xml.xsom.XSAnnotation;
import com.sun.xml.xsom.XSComponent;

public final class XSComponentHelper {

	private XSComponentHelper() {
		// no constructor for utility class
	}

	public static String getDocumentation(XSComponent schemaComponent) {
		if (schemaComponent == null) {
			return null;
		}
		XSAnnotation xsAnnotation = schemaComponent.getAnnotation();
		if (xsAnnotation == null) {
			return null;
		}
		BindInfo annotation = (BindInfo) xsAnnotation.getAnnotation();
		if (annotation == null) {
			return null;
		}
		String documentation = annotation.getDocumentation();
		return documentation;
	}

}

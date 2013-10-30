/**
 * 
 */
package org.dpytel.jaxb.xjc.javadoc;

import java.util.Collection;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

import com.sun.codemodel.JFieldVar;
import com.sun.tools.xjc.Options;
import com.sun.tools.xjc.Plugin;
import com.sun.tools.xjc.model.CPropertyInfo;
import com.sun.tools.xjc.outline.ClassOutline;
import com.sun.tools.xjc.outline.EnumOutline;
import com.sun.tools.xjc.outline.FieldOutline;
import com.sun.tools.xjc.outline.Outline;
import com.sun.tools.xjc.reader.xmlschema.bindinfo.BindInfo;
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
		JavadocInserter javadocInserter = new JavadocInserter(outline, opt, errorHandler);
		return javadocInserter.addJavadocs();
	}

}

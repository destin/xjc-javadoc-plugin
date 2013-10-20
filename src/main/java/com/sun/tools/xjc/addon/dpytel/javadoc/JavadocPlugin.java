/**
 * 
 */
package com.sun.tools.xjc.addon.dpytel.javadoc;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

import com.sun.tools.xjc.Options;
import com.sun.tools.xjc.Plugin;
import com.sun.tools.xjc.outline.Outline;

/**
 * Thin wrapper around the DefaultValuePlugin. This must be in the com.sun.tools.xjc.addon package
 * for it to work with Java 6. See https://issues.apache.org/jira/browse/CXF-1880.
 */
public class JavadocPlugin extends Plugin {
	
	private org.dpytel.jaxb.xjc.javadoc.JavadocPlugin impl = new org.dpytel.jaxb.xjc.javadoc.JavadocPlugin();

	/* (non-Javadoc)
	 * @see com.sun.tools.xjc.Plugin#getOptionName()
	 */
	@Override
	public String getOptionName() {
		return impl.getOptionName();
	}

	/* (non-Javadoc)
	 * @see com.sun.tools.xjc.Plugin#getUsage()
	 */
	@Override
	public String getUsage() {
		return impl.getUsage();
	}

	/* (non-Javadoc)
	 * @see com.sun.tools.xjc.Plugin#run(com.sun.tools.xjc.outline.Outline, com.sun.tools.xjc.Options, org.xml.sax.ErrorHandler)
	 */
	@Override
	public boolean run(Outline outline, Options opt, ErrorHandler errorHandler)
			throws SAXException {
		return impl.run(outline, opt, errorHandler);
	}

}

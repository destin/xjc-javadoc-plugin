package org.dpytel.jaxb.xjc.javadoc;

import java.util.Collection;

import com.sun.codemodel.JMethod;
import com.sun.tools.xjc.outline.ClassOutline;

public final class MethodHelper {

	private MethodHelper() {
		// no constructor for helper class
	}

	/**
	 * Find method in given class with given method name
	 * 
	 * @param classOutline
	 * @param methodName
	 * @return method in given class with given method name
	 */
	public static JMethod findMethod(ClassOutline classOutline,
			String methodName) {
		Collection<JMethod> methods = classOutline.implClass.methods();
		for (JMethod method : methods) {
			if (method.name().equals(methodName)) {
				return method;
			}
		}
		return null;
	}

}

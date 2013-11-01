/**
 * 
 */
package org.dpytel.jaxb.xjc.javadoc;

import java.util.List;

import org.eclipse.jdt.core.dom.Javadoc;
import org.eclipse.jdt.core.dom.TagElement;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * Utility methods for tests
 * 
 * @author Dawid Pytel
 * 
 */
public class JavadocTestHelper {

	public static Matcher<Javadoc> javadocContains(final String comment) {
		return new TypeSafeMatcher<Javadoc>(Javadoc.class) {

			@Override
			protected boolean matchesSafely(Javadoc javadoc) {
				TagElement tagElement = (TagElement) javadoc.tags().get(0);
				List<?> fragments = tagElement.fragments();
				for (Object fragment : fragments) {
					if (fragment != null
							&& fragment.toString().contains(comment)) {
						return true;
					}
				}
				return false;
			}

			public void describeTo(Description description) {
				description.appendText("javadoc contains given comment: "
						+ comment);
			}
		};
	}
}

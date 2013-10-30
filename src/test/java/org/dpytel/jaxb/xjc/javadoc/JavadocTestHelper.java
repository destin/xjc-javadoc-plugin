/**
 * 
 */
package org.dpytel.jaxb.xjc.javadoc;

import static org.hamcrest.CoreMatchers.containsString;

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
				Object firstFragment = tagElement.fragments().get(0);
				return containsString(comment)
						.matches(firstFragment.toString());
			}

			public void describeTo(Description description) {
				description
						.appendText("Expected that javadoc contains given comment: "
								+ comment);
			}
		};
	}
}

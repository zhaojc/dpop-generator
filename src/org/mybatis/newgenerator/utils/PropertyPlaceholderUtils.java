package org.mybatis.newgenerator.utils;

import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * 
 * @author huhailiang
 * @date 2014-6-28下午2:41:44
 */
public class PropertyPlaceholderUtils {
	
	
	/** Default placeholder prefix: {@value} */
	public static final String DEFAULT_PLACEHOLDER_PREFIX = "${";

	/** Default placeholder suffix: {@value} */
	public static final String DEFAULT_PLACEHOLDER_SUFFIX = "}";

	/** Default value separator: {@value} */
	public static final String DEFAULT_VALUE_SEPARATOR = ":";

	
	public static String replacePlaceholders(String value, final Properties properties) {
		return replacePlaceholders(value, new PlaceholderResolver() {
			public String resolvePlaceholder(String placeholderName) {
				return properties.getProperty(placeholderName);
			}
		});
	}
	
	public static String replacePlaceholders(String value, final Map<String,String> properties) {
		return replacePlaceholders(value, new PlaceholderResolver() {
			public String resolvePlaceholder(String placeholderName) {
				return properties.get(placeholderName);
			}
		});
	}
	
	public static String replacePlaceholders(String value, PlaceholderResolver placeholderResolver) {
		return parseStringValue(value, placeholderResolver, new HashSet<String>());
	}
	
	protected static String parseStringValue(
			String strValule, PlaceholderResolver placeholderResolver, Set<String> visitedPlaceholders) {

		String strVal = (null == strValule)?"":strValule;
		StringBuilder buf = new StringBuilder(strVal);

		int startIndex = strVal.indexOf(DEFAULT_PLACEHOLDER_PREFIX);
		while (startIndex != -1) {
			int endIndex = findPlaceholderEndIndex(buf, startIndex);
			if (endIndex != -1) {
				String placeholder = buf.substring(startIndex + DEFAULT_PLACEHOLDER_PREFIX.length(), endIndex);
				if (!visitedPlaceholders.add(placeholder)) {
					throw new IllegalArgumentException(
							"Circular placeholder reference '" + placeholder + "' in property definitions");
				}
				// Recursive invocation, parsing placeholders contained in the placeholder key.
				placeholder = parseStringValue(placeholder, placeholderResolver, visitedPlaceholders);

				// Now obtain the value for the fully resolved key...
				String propVal = placeholderResolver.resolvePlaceholder(placeholder);
				if (propVal == null && DEFAULT_VALUE_SEPARATOR != null) {
					int separatorIndex = placeholder.indexOf(DEFAULT_VALUE_SEPARATOR);
					if (separatorIndex != -1) {
						String actualPlaceholder = placeholder.substring(0, separatorIndex);
						String defaultValue = placeholder.substring(separatorIndex + DEFAULT_VALUE_SEPARATOR.length());
						propVal = placeholderResolver.resolvePlaceholder(actualPlaceholder);
						if (propVal == null) {
							propVal = defaultValue;
						}
					}
				}
				if (propVal != null) {
					// Recursive invocation, parsing placeholders contained in the
					// previously resolved placeholder value.
					propVal = parseStringValue(propVal, placeholderResolver, visitedPlaceholders);
					buf.replace(startIndex, endIndex + DEFAULT_PLACEHOLDER_SUFFIX.length(), propVal);
					startIndex = buf.indexOf(DEFAULT_PLACEHOLDER_PREFIX, startIndex + propVal.length());
				}
				else {
					throw new IllegalArgumentException("Could not resolve placeholder '" +
							placeholder + "'" + " in string value [" + strVal + "]");
				}

				visitedPlaceholders.remove(placeholder);
			}
			else {
				startIndex = -1;
			}
		}

		return buf.toString();
	}
	
	
	
	private static int findPlaceholderEndIndex(CharSequence buf, int startIndex) {
		int index = startIndex + DEFAULT_PLACEHOLDER_PREFIX.length();
		int withinNestedPlaceholder = 0;
		while (index < buf.length()) {
			if (substringMatch(buf, index, DEFAULT_PLACEHOLDER_SUFFIX)) {
				if (withinNestedPlaceholder > 0) {
					withinNestedPlaceholder--;
					index = index + DEFAULT_PLACEHOLDER_SUFFIX.length();
				}
				else {
					return index;
				}
			}
			else if (substringMatch(buf, index, DEFAULT_PLACEHOLDER_SUFFIX)) {
				withinNestedPlaceholder++;
				index = index + DEFAULT_PLACEHOLDER_SUFFIX.length();
			}
			else {
				index++;
			}
		}
		return -1;
	}
	/**
	 * Test whether the given string matches the given substring
	 * at the given index.
	 * @param str the original string (or StringBuilder)
	 * @param index the index in the original string to start matching against
	 * @param substring the substring to match at the given index
	 */
	private static boolean substringMatch(CharSequence str, int index, CharSequence substring) {
		for (int j = 0; j < substring.length(); j++) {
			int i = index + j;
			if (i >= str.length() || str.charAt(i) != substring.charAt(j)) {
				return false;
			}
		}
		return true;
	}
	/**
	 * Strategy interface used to resolve replacement values for placeholders contained in Strings.
	 * @see PropertyPlaceholderHelper
	 */
	public static interface PlaceholderResolver {

		/**
		 * Resolves the supplied placeholder name into the replacement value.
		 * @param placeholderName the name of the placeholder to resolve.
		 * @return the replacement value or <code>null</code> if no replacement is to be made.
		 */
		String resolvePlaceholder(String placeholderName);
	}
	
	
	
}

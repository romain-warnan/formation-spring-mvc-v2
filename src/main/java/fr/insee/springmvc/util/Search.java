package fr.insee.springmvc.util;

import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;

public abstract class Search {

	private Search() {
		// Private
	}

	public static String normalize(String q) {
		String s = RegExUtils.removeAll(q, "[-’()]");
		s = StringUtils.stripAccents(s);
		s = StringUtils.normalizeSpace(s);
		s = StringUtils.upperCase(s);
		return s;
	}

}

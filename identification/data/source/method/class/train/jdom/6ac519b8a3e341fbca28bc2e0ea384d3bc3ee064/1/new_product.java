public static String checkAttributeName(final String name) {
		// Attribute names may not be xmlns since we do this internally too
		if ("xmlns".equals(name)) {
			return "An Attribute name may not be \"xmlns\"; " +
					"use the Namespace class to manage namespaces";
		}

		return checkJDOMName(name);
	}
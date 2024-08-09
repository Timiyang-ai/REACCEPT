public static String checkAttributeName(final String name) {
		// Check basic XML name rules first
		if (checkXMLName(name) != null) {
			return checkXMLName(name);
		}

		// No colons are allowed, since attributes handle this internally
		if (name.indexOf(":") != -1) {
			return "Attribute names cannot contain colons";
		}

		// Attribute names may not be xmlns since we do this internally too
		if (name.equals("xmlns")) {
			return "An Attribute name may not be \"xmlns\"; " +
					"use the Namespace class to manage namespaces";
		}

		// If we got here, everything is OK
		return null;
	}
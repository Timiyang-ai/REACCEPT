public static String getElementAttributes(Element element, ImmutableSet<String> exclude) {
		StringBuilder buffer = new StringBuilder();
		if (element != null) {
			NamedNodeMap attributes = element.getAttributes();
			if (attributes != null) {
				addAttributesToString(exclude, buffer, attributes);
			}
		}

		return buffer.toString().trim();
	}
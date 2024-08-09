public static String getElementAttributes(Element element, ImmutableSet<String> exclude) {
		StringBuilder buffer = new StringBuilder();

		if (element != null) {
			NamedNodeMap attributes = element.getAttributes();
			if (attributes != null) {
				for (int i = 0; i < attributes.getLength(); i++) {
					Attr attr = (Attr) attributes.item(i);
					if (!exclude.contains(attr.getNodeName())) {
						buffer.append(attr.getNodeName()).append('=');
						buffer.append(attr.getNodeValue()).append(' ');
					}
				}
			}
		}

		return buffer.toString().trim();
	}
public String getNamespaceURI(String prefix) {
		if (prefix == null) {
			throw new NullPointerException("Null prefix");
		} else if ("html".equals(prefix)) {
			return "http://www.w3.org/1999/xhtml";
		} else if ("xml".equals(prefix)) {
			return XMLConstants.XML_NS_URI;
		}

		return XMLConstants.DEFAULT_NS_PREFIX;
	}
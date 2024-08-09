public static String stripXPathToElement(String xpath) {
		if (!Strings.isNullOrEmpty(xpath)) {
			if (xpath.toLowerCase().contains("/text()")) {
				xpath = xpath.substring(0, xpath.toLowerCase().indexOf("/text()"));
			}
			if (xpath.toLowerCase().contains("/comment()")) {
				xpath = xpath.substring(0, xpath.toLowerCase().indexOf("/comment()"));
			}
			if (xpath.contains("@")) {
				xpath = xpath.substring(0, xpath.indexOf("@") - 1);
			}
		}

		return xpath;
	}
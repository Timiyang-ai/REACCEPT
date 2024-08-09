public static String stripXPathToElement(String xpath) {
		if (xpath != null && !xpath.equals("")) {
			if (xpath.toLowerCase().indexOf("/text()") != -1) {
				xpath = xpath.substring(0, xpath.toLowerCase().indexOf("/text()"));
			}
			if (xpath.toLowerCase().indexOf("/comment()") != -1) {
				xpath = xpath.substring(0, xpath.toLowerCase().indexOf("/comment()"));
			}
			if (xpath.indexOf("@") != -1) {
				xpath = xpath.substring(0, xpath.indexOf("@") - 1);
			}
		}

		return xpath;
	}
public static String stripXPathToElement(String xpath) {
		String xpathStripped = xpath; 
		
		if (!Strings.isNullOrEmpty(xpathStripped)) {
			if (xpathStripped.toLowerCase().contains("/text()")) {
				xpathStripped = xpathStripped.substring(0, xpathStripped.toLowerCase().indexOf("/text()"));
			}
			if (xpathStripped.toLowerCase().contains("/comment()")) {
				xpathStripped = xpathStripped.substring(0, xpathStripped.toLowerCase().indexOf("/comment()"));
			}
			if (xpathStripped.contains("@")) {
				xpathStripped = xpathStripped.substring(0, xpathStripped.indexOf("@") - 1);
			}
		}

		return xpathStripped;
	}
public static String formatXPath(String xpath) {
		String formatted = capitalizeTagNames(xpath);
		formatted = lowerCaseAttributes(formatted);
		return formatted;
	}
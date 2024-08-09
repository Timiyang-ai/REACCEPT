public static String formatXPath(String xpath) {
		String formatted = xpath;
		Matcher tagMatcher = TAG_PATTERN.matcher(formatted);
		tagMatcher.find();
		formatted =
		        tagMatcher.replaceFirst(tagMatcher.group(1) + tagMatcher.group(2).toUpperCase());

		Matcher IdMatcher = ID_PATTERN.matcher(formatted);
		for (int i = 0; IdMatcher.find(i); i++) {
			i = IdMatcher.start();
			formatted = IdMatcher.replaceFirst(IdMatcher.group().toLowerCase());
			IdMatcher = ID_PATTERN.matcher(formatted);
		}
		return formatted;
	}
public static String get(Pattern pattern, String content, int groupIndex) {
		Matcher matcher = pattern.matcher(content);
		if (matcher.find()) {
			return matcher.group(groupIndex);
		}
		return null;
	}
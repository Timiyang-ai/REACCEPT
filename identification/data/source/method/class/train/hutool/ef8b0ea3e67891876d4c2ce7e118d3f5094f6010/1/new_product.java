public static String replaceAll(String content, String regex, String replacementTemplate) {
		final Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
		return replaceAll(content, pattern, replacementTemplate);
	}
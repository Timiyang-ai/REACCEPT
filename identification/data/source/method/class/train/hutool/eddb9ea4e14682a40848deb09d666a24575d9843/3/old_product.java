public static String extractMulti(String regex, String content, String template) {
		Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
		return extractMulti(pattern, content, template);
	}
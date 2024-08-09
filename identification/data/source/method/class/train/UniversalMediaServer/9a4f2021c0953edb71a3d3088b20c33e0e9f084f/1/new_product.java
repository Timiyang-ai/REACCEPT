public static String stripHTML(String html) {
		Pattern pattern = Pattern.compile("<body>(.*)</body>", Pattern.CASE_INSENSITIVE + Pattern.DOTALL);
		Matcher matcher = pattern.matcher(html);
		if (matcher.find()) {
			return matcher.group(1).replaceAll("\n    ", "").trim().replaceAll("(?i)<br>", "\n").replaceAll("<.*?>","");
		}
		throw new IllegalArgumentException("HTML text not as expected, must have <body> section");
	}
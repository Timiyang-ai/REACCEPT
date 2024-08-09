public static String formatXPath(String xpath) {
		String formatted = xpath;
		Pattern p = Pattern.compile("(/[a-z]+)");
		Matcher m = p.matcher(xpath);

		while (m.find()) {
			formatted = m.replaceFirst(m.group().toUpperCase());
			m = p.matcher(formatted);
		}
		p = Pattern.compile("(@[A-Z]+)");
		m = p.matcher(formatted);

		while (m.find()) {
			formatted = m.replaceFirst(m.group().toLowerCase());
			m = p.matcher(formatted);
		}
		return formatted;
	}
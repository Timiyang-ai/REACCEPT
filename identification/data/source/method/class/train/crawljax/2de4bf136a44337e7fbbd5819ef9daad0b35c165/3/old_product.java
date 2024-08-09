public static String formatXPath(String xpath) {
		String formatted = xpath;
		Pattern p = Pattern.compile("(/(?:[-a-zA-Z]+::)?+)([a-zA-Z]+)");
		Matcher m = p.matcher(formatted);

		for (int i = 0; m.find(i); i++) {
			i = m.start();
			formatted = m.replaceFirst(m.group(1) + m.group(2).toUpperCase());
			m = p.matcher(formatted);
		}
		p = Pattern.compile("(@[a-zA-Z]+)");
		m = p.matcher(formatted);

		for (int i = 0; m.find(i); i++) {
			i = m.start();
			formatted = m.replaceFirst(m.group().toLowerCase());
			m = p.matcher(formatted);
		}
		return formatted;
	}
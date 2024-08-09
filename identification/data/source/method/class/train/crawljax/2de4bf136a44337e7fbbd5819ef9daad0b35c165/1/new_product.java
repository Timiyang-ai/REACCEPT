public static String formatXPath(String xpath) {
		Matcher m = TAG_PATTERN.matcher(xpath);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			String text = m.group();
			System.out.println("Found group " + text);
			m.appendReplacement(sb, Matcher.quoteReplacement(text.toUpperCase()));
		}
		m.appendTail(sb);

		m = ID_PATTERN.matcher(sb.toString());
		sb = new StringBuffer();
		while (m.find()) {
			String text = m.group();
			m.appendReplacement(sb, Matcher.quoteReplacement(text.toLowerCase()));
		}
		m.appendTail(sb);

		System.out.println(xpath + " became " + sb.toString());

		return sb.toString();
	}
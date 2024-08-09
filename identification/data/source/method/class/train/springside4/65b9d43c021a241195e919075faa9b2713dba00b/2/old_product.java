public static String urlEncode(String part) {
		try {
			return URLEncoder.encode(part, Charsets.UTF_8_NAME);
		} catch (UnsupportedEncodingException ignored) {
			return null;
		}
	}
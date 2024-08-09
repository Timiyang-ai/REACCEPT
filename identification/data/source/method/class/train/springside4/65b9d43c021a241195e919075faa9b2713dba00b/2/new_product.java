public static String urlEncode(String part) {
		try {
			return URLEncoder.encode(part, Platforms.UTF_8);
		} catch (UnsupportedEncodingException ignored) {
			return null;
		}
	}
public static String downloadString(String url, String customCharset) throws IOException {
		InputStream inputStream = new URL(url).openStream();
		return IoUtil.getString(inputStream, customCharset);
	}
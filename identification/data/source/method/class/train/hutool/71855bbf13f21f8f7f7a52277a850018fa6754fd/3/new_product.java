public static String downloadString(String url, String customCharsetName) {
		return downloadString(url, CharsetUtil.charset(customCharsetName), null);
	}
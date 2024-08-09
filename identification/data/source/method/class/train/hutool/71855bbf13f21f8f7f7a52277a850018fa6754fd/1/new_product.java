public static String downloadString(String url, String customCharset) throws IOException {
		InputStream in = null;
		try {
			in = new URL(url).openStream();
			return IoUtil.getString(in, customCharset);
		} finally {
			FileUtil.close(in);
		}
	}
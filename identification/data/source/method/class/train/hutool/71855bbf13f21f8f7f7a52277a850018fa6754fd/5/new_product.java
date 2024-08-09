public static String downloadString(String url, String customCharset) throws IOException {
		if(StrUtil.isBlank(url)){
			throw new NullPointerException("[url] is null!");
		}
		
		InputStream in = null;
		try {
			in = new URL(url).openStream();
			return IoUtil.read(in, customCharset);
		} finally {
			IoUtil.close(in);
		}
	}
public static String downloadString(String url, Charset customCharset, StreamProgress streamPress) {
		if(StrUtil.isBlank(url)){
			throw new NullPointerException("[url] is null!");
		}
		
		FastByteArrayOutputStream out = new FastByteArrayOutputStream();
		download(url, out, true, null);
		return null == customCharset ? out.toString() : out.toString(customCharset);
	}
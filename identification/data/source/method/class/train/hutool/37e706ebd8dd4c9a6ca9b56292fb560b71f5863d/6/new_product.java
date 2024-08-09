public static String toParams(Map<String, Object> paramMap, String charsetName) {
		return toParams(paramMap, CharsetUtil.charset(charsetName));
	}
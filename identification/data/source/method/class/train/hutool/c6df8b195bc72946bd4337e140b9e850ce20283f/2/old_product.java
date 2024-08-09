public static String toUnicodeHex(int value) {
		final StringBuilder builder = new StringBuilder(7);
		
		builder.append("\\u");
		String hex = Integer.toHexString(value);
		int len = hex.length();
		if(len < 4) {
			builder.append("0000", 0, 4 - len);//不足4位补0
		}
		builder.append(hex);
		
		return builder.toString();
	}
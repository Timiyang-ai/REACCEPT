public static String toUnicodeHex(char ch) {
		StringBuilder sb = new StringBuilder(6);
		sb.append("\\u");
		sb.append(DIGITS_LOWER[(ch >> 12) & 15]);
		sb.append(DIGITS_LOWER[(ch >> 8) & 15]);
		sb.append(DIGITS_LOWER[(ch >> 4) & 15]);
		sb.append(DIGITS_LOWER[(ch) & 15]);
		return sb.toString();
	}
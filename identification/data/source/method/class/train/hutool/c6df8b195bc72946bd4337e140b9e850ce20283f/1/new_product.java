public static String toUnicodeHex(char ch) {
		return "\\u" +//
				DIGITS_LOWER[(ch >> 12) & 15] +//
				DIGITS_LOWER[(ch >> 8) & 15] +//
				DIGITS_LOWER[(ch >> 4) & 15] +//
				DIGITS_LOWER[(ch) & 15];
	}
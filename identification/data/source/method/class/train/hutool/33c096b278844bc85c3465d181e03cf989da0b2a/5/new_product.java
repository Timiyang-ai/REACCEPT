public static String cleanBlank(CharSequence str) {
		if (str == null) {
			return null;
		}

		int len = str.length();
		StringBuilder sb = new StringBuilder(str.length());
		char c;
		for (int i = 0; i < len; i++) {
			c = str.charAt(i);
			if (false == NumberUtil.isBlankChar(c)) {
				sb.append(c);
			}
		}
		return sb.toString();
	}
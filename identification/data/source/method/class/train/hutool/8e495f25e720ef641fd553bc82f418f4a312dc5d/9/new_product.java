public static String[] split(CharSequence str, int len) {
		int partCount = str.length() / len;
		int lastPartCount = str.length() % len;
		int fixPart = 0;
		if (lastPartCount != 0) {
			fixPart = 1;
		}
		
		final String str2 = str.toString();
		final String[] strs = new String[partCount + fixPart];
		for (int i = 0; i < partCount + fixPart; i++) {
			if (i == partCount + fixPart - 1 && lastPartCount != 0) {
				strs[i] = str2.substring(i * len, i * len + lastPartCount);
			} else {
				strs[i] = str2.substring(i * len, i * len + len);
			}
		}
		return strs;
	}
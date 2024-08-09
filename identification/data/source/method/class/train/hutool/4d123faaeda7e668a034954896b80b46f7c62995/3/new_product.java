public static String trim(CharSequence str, int mode) {
		if (str == null) {
			return null;
		}

		int length = str.length();
		int start = 0;
		int end = length;

		// 扫描字符串头部
		if (mode <= 0) {
			while ((start < end) && (NumberUtil.isBlankChar(str.charAt(start)))) {
				start++;
			}
		}

		// 扫描字符串尾部
		if (mode >= 0) {
			while ((start < end) && (NumberUtil.isBlankChar(str.charAt(end - 1)))) {
				end--;
			}
		}

		if ((start > 0) || (end < length)) {
			return str.toString().substring(start, end);
		}

		return str.toString();
	}
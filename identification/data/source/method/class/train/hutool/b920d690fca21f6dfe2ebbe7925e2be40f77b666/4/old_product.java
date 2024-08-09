public static boolean isNumber(String value) {
		if (StrUtil.isBlank(value)) {
			return false;
		}
		return isMactchRegex(NUMBERS, value);
	}
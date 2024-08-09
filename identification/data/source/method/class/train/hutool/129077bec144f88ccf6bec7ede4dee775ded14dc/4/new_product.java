public static boolean isInteger(String s) {
		if (StrUtil.isNotBlank(s)) {
			return s.matches("^\\d+$");
		} else {
			return false;
		}
	}
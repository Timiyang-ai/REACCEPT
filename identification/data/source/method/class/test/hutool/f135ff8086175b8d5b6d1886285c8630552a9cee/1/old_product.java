public static String lowerFirst(String str) {
		if (isBlank(str)) {
			return str;
		}
		return Character.toLowerCase(str.charAt(0)) + subSuf(str, 1);
	}
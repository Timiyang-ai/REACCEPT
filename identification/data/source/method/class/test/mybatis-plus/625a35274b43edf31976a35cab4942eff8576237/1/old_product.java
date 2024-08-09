public static boolean isCapitalMode(String word) {
		if (null == word) {
			return false;
		}
		return word.matches("^[0-9A-Z/_]+$");
	}
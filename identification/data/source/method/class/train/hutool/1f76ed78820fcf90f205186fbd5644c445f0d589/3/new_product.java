public static Integer getFirstNumber(String StringWithNumber) {
		return Convert.toInt(get(PatternPool.NUMBERS, StringWithNumber, 0), null);
	}
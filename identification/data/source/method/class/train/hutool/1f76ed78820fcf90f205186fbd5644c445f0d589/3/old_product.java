public static Integer getFirstNumber(String StringWithNumber) {
		return Convert.toInt(get(Validator.NUMBERS, StringWithNumber, 0), null);
	}
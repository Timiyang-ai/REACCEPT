public static Integer getFirstNumber(String StringWithNumber) {
		return Conver.toInt(get(Validator.NUMBERS, StringWithNumber, 0), null);
	}
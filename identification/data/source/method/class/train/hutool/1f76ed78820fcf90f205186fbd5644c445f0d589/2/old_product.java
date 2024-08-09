public static Integer getFirstNumber(String StringWithNumber) {
		return Conver.toInt(get(Validator.NUMBER, StringWithNumber, 0), null);
	}
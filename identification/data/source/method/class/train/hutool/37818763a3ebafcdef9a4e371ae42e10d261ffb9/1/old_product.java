public static String roundStr(double number, int digit) {
		return String.format("%." + digit + 'f', number);
	}
public static String decimalFormat(String pattern, long value) {
		return new DecimalFormat(pattern).format(value);
	}
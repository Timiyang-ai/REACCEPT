public static double round(String numberStr, int scale) {
		return round(numberStr, scale, RoundingMode.HALF_UP);
	}
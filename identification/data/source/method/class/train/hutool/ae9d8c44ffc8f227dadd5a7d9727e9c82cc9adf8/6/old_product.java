public static double round(String numberStr, int scale, RoundingMode roundingMode) {
		final BigDecimal b = new BigDecimal(numberStr);
		return b.setScale(scale, roundingMode).doubleValue();
	}
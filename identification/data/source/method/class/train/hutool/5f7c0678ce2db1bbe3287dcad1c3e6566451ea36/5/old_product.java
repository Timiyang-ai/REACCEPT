public static BigDecimal div(String v1, String v2, int scale, RoundingMode roundingMode) {
		if (scale < 0) {
			scale = -scale;
		}
		final BigDecimal b1 = new BigDecimal(v1);
		final BigDecimal b2 = new BigDecimal(v2);
		return b1.divide(b2, scale, roundingMode);
	}
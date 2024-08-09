public static BigDecimal div(String v1, String v2, int scale, RoundingMode roundingMode) {
		return div(new BigDecimal(v1), new BigDecimal(v1), scale, roundingMode);
	}
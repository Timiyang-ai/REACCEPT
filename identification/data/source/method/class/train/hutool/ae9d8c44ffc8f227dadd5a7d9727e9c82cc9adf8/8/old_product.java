public static double round(double v, int scale, RoundingMode roundingMode) {
		BigDecimal b = new BigDecimal(v);
		return b.setScale(scale, roundingMode).doubleValue();
	}
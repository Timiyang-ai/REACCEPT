public static double div(double v1, double v2, int scale, RoundingMode roundingMode) {
		if (scale < 0) {
			scale = -scale;
		}
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, scale, roundingMode).doubleValue();
	}
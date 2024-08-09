public static double div(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.divide(b2, 3, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
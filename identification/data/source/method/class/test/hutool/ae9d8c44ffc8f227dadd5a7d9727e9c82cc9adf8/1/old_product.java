public static double round(double v, int scale) {
		BigDecimal b = new BigDecimal(v);
		return b.setScale(scale, RoundingMode.HALF_UP).doubleValue();
	}
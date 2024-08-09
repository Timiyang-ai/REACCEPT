public static BigDecimal add(String v1, String v2) {
		final BigDecimal b1 = new BigDecimal(v1);
		final BigDecimal b2 = new BigDecimal(v2);
		return b1.add(b2);
	}
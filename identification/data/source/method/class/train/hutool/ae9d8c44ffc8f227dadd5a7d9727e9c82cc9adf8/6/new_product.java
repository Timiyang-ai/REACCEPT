public static BigDecimal round(String numberStr, int scale, RoundingMode roundingMode) {
		Assert.notBlank(numberStr);
		if(scale < 0) {
			scale = 0;
		}
		if(null == roundingMode) {
			roundingMode = RoundingMode.HALF_UP;
		}
		
		final BigDecimal b = new BigDecimal(numberStr);
		return b.setScale(scale, roundingMode);
	}
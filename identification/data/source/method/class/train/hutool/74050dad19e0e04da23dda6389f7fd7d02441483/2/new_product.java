public static boolean equals(BigDecimal bigNum1, BigDecimal bigNum2) {
		Assert.notNull(bigNum1);
		Assert.notNull(bigNum2);
		return 0 == bigNum1.compareTo(bigNum2);
	}
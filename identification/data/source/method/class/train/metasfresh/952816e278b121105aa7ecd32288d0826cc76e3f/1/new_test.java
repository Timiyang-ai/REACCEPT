	@Test
	public void roundToHalf()
	{
		assertThat(Percent.of(new BigDecimal("10.01")).roundToHalf(RoundingMode.HALF_UP).toBigDecimal()).isEqualByComparingTo("10.0");
		assertThat(Percent.of(new BigDecimal("10.32")).roundToHalf(RoundingMode.HALF_UP).toBigDecimal()).isEqualByComparingTo("10.5");
	}
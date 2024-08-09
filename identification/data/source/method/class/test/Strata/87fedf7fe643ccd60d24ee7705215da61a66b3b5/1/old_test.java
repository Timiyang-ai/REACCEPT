  @Test
  public void test_withCurrency() {
    InflationRateSensitivity base = InflationRateSensitivity.of(CH_CPI_OBS, 3.5);
    assertThat(base.withCurrency(CHF)).isEqualTo(base);
    InflationRateSensitivity expected = InflationRateSensitivity.of(CH_CPI_OBS, USD, 3.5);
    InflationRateSensitivity test = base.withCurrency(USD);
    assertThat(test).isEqualTo(expected);
  }
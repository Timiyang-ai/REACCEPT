  @Test
  public void test_multipliedBy() {
    InflationRateSensitivity base = InflationRateSensitivity.of(CH_CPI_OBS, 5.0);
    InflationRateSensitivity expected = InflationRateSensitivity.of(CH_CPI_OBS, 2.6 * 5.0);
    InflationRateSensitivity test = base.multipliedBy(2.6d);
    assertThat(test).isEqualTo(expected);
  }
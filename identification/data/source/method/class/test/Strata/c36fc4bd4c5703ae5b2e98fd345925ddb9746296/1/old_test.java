  @Test
  public void convert() {
    assertThat(fxRateProvider.convert(2, Currency.GBP, Currency.GBP, 0)).isEqualTo(2d);
    assertThat(fxRateProvider.convert(10, Currency.GBP, Currency.USD, 0)).isEqualTo(14d);
  }
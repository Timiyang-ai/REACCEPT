  @Test
  public void fxRate() {
    assertThat(fxRateProvider.fxRate(Currency.GBP, Currency.GBP, 0)).isEqualTo(1d);
    assertThat(fxRateProvider.fxRate(Currency.GBP, Currency.USD, 0)).isEqualTo(1.4d);
  }
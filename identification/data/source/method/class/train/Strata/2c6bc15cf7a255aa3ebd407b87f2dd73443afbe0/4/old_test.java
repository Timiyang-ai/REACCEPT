  @Test
  public void fxRate() {
    double eurUsdRate = provider().fxRate(EUR, USD);
    assertThat(eurUsdRate).isEqualTo(1.1d);
  }
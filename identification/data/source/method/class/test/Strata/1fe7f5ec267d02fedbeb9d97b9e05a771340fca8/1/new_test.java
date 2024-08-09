  @Test
  public void test_fxRate_separate() {
    ImmutableRatesProvider test = ImmutableRatesProvider.builder(VAL_DATE)
        .fxRateProvider(FX_MATRIX)
        .build();
    assertThat(test.fxRate(USD, GBP)).isCloseTo(1 / FX_GBP_USD, offset(0d));
    assertThat(test.fxRate(USD, USD)).isCloseTo(1d, offset(0d));
  }
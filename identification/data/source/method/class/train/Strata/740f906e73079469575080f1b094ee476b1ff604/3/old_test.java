  @Test
  public void test_rate_beforeValuation_fixing() {
    DiscountIborIndexRates test = DiscountIborIndexRates.of(GBP_LIBOR_3M, DFCURVE, SERIES);
    assertThat(test.rate(GBP_LIBOR_3M_BEFORE)).isEqualTo(RATE_BEFORE);
  }
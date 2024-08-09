  @Test
  public void test_withDiscountFactors() {
    DiscountIborIndexRates test = DiscountIborIndexRates.of(GBP_LIBOR_3M, DFCURVE, SERIES);
    test = test.withDiscountFactors(DFCURVE2);
    assertThat(test.getIndex()).isEqualTo(GBP_LIBOR_3M);
    assertThat(test.getValuationDate()).isEqualTo(DATE_VAL);
    assertThat(test.getFixings()).isEqualTo(SERIES);
    assertThat(test.getDiscountFactors()).isEqualTo(DFCURVE2);
  }
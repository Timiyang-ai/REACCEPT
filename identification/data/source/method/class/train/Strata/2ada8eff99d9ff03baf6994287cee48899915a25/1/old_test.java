  @Test
  public void test_withDiscountFactors() {
    DiscountOvernightIndexRates test = DiscountOvernightIndexRates.of(EUR_EONIA, DFCURVE, SERIES);
    test = test.withDiscountFactors(DFCURVE2);
    assertThat(test.getIndex()).isEqualTo(EUR_EONIA);
    assertThat(test.getValuationDate()).isEqualTo(DATE_VAL);
    assertThat(test.getFixings()).isEqualTo(SERIES);
    assertThat(test.getDiscountFactors()).isEqualTo(DFCURVE2);
  }
  @Test
  public void test_ratePointSensitivity_fixing() {
    DiscountOvernightIndexRates test = DiscountOvernightIndexRates.of(EUR_EONIA, DFCURVE, SERIES);
    assertThat(test.ratePointSensitivity(EUR_EONIA_BEFORE)).isEqualTo(PointSensitivityBuilder.none());
    assertThat(test.ratePointSensitivity(EUR_EONIA_VAL)).isEqualTo(PointSensitivityBuilder.none());
  }
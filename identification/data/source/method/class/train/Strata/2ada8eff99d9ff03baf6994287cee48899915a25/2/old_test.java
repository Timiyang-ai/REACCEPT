  @Test
  public void test_ratePointSensitivity_fixing() {
    DiscountIborIndexRates test = DiscountIborIndexRates.of(GBP_LIBOR_3M, DFCURVE, SERIES);
    assertThat(test.ratePointSensitivity(GBP_LIBOR_3M_BEFORE)).isEqualTo(PointSensitivityBuilder.none());
    assertThat(test.ratePointSensitivity(GBP_LIBOR_3M_VAL)).isEqualTo(PointSensitivityBuilder.none());
  }
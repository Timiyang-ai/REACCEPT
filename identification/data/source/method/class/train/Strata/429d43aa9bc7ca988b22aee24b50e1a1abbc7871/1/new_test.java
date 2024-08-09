  @Test
  public void test_valuePointSensitivity_fixing() {
    SimplePriceIndexValues test = SimplePriceIndexValues.of(US_CPI_U, VAL_DATE, CURVE_NOFIX, USCPI_TS);
    PriceIndexObservation obs = PriceIndexObservation.of(US_CPI_U, VAL_MONTH.minusMonths(3));
    assertThat(test.valuePointSensitivity(obs)).isEqualTo(PointSensitivityBuilder.none());
  }
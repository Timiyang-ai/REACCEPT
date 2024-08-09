  @Test
  public void test_withCurve() {
    SimplePriceIndexValues test = SimplePriceIndexValues.of(US_CPI_U, VAL_DATE, CURVE_NOFIX, USCPI_TS).withCurve(CURVE2_NOFIX);
    assertThat(test.getCurve()).isEqualTo(CURVE2_NOFIX);
  }
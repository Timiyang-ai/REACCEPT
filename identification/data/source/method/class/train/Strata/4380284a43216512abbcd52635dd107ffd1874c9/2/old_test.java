  @Test
  public void test_fxIndexRates() {
    LocalDateDoubleTimeSeries ts = LocalDateDoubleTimeSeries.of(VAL_DATE, 0.62d);
    ImmutableRatesProvider test = ImmutableRatesProvider.builder(VAL_DATE)
        .fxRateProvider(FX_MATRIX)
        .discountCurve(GBP, DISCOUNT_CURVE_GBP)
        .discountCurve(USD, DISCOUNT_CURVE_USD)
        .timeSeries(GBP_USD_WM, ts)
        .build();
    assertThat(test.fxIndexRates(GBP_USD_WM).getIndex()).isEqualTo(GBP_USD_WM);
    assertThat(test.fxIndexRates(GBP_USD_WM).getFixings()).isEqualTo(ts);
    assertThat(test.getTimeSeriesIndices()).containsOnly(GBP_USD_WM);
  }
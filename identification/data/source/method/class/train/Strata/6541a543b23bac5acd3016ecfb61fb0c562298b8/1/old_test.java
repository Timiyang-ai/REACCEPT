  @Test
  public void test_overnightIndexRates() {
    LocalDateDoubleTimeSeries ts = LocalDateDoubleTimeSeries.of(VAL_DATE, 0.62d);
    ImmutableRatesProvider test = ImmutableRatesProvider.builder(VAL_DATE)
        .overnightIndexCurve(USD_FED_FUND, FED_FUND_CURVE)
        .timeSeries(USD_FED_FUND, ts)
        .build();
    assertThat(test.overnightIndexRates(USD_FED_FUND).getIndex()).isEqualTo(USD_FED_FUND);
    assertThat(test.overnightIndexRates(USD_FED_FUND).getFixings()).isEqualTo(ts);
    assertThat(test.getOvernightIndices()).containsOnly(USD_FED_FUND);
    assertThat(test.getTimeSeriesIndices()).containsOnly(USD_FED_FUND);
  }
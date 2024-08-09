  @Test
  public void test_iborIndexRates() {
    LocalDateDoubleTimeSeries ts = LocalDateDoubleTimeSeries.of(VAL_DATE, 0.62d);
    ImmutableRatesProvider test = ImmutableRatesProvider.builder(VAL_DATE)
        .iborIndexCurve(USD_LIBOR_3M, USD_LIBOR_CURVE)
        .timeSeries(USD_LIBOR_3M, ts)
        .build();
    assertThat(test.iborIndexRates(USD_LIBOR_3M).getIndex()).isEqualTo(USD_LIBOR_3M);
    assertThat(test.iborIndexRates(USD_LIBOR_3M).getFixings()).isEqualTo(ts);
    assertThat(test.getIborIndices()).containsOnly(USD_LIBOR_3M);
    assertThat(test.getTimeSeriesIndices()).containsOnly(USD_LIBOR_3M);
  }
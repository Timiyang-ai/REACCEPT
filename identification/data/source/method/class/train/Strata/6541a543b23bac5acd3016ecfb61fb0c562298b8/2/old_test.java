  @Test
  public void test_priceIndexValues() {
    LocalDateDoubleTimeSeries ts = LocalDateDoubleTimeSeries.of(VAL_DATE, 0.62d);
    ImmutableRatesProvider test = ImmutableRatesProvider.builder(VAL_DATE)
        .priceIndexCurve(GB_RPI, GBPRI_CURVE)
        .timeSeries(GB_RPI, ts)
        .build();
    assertThat(test.priceIndexValues(GB_RPI).getIndex()).isEqualTo(GB_RPI);
    assertThat(test.priceIndexValues(GB_RPI).getFixings()).isEqualTo(ts);
    assertThat(test.getPriceIndices()).containsOnly(GB_RPI);
    assertThat(test.getTimeSeriesIndices()).containsOnly(GB_RPI);
  }
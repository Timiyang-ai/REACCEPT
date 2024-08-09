  @Test
  public void test_builder() {
    LocalDateDoubleTimeSeries ts = LocalDateDoubleTimeSeries.of(PREV_DATE, 0.62d);
    ImmutableRatesProvider test = ImmutableRatesProvider.builder(VAL_DATE)
        .timeSeries(GBP_USD_WM, ts)
        .build();
    assertThat(test.getValuationDate()).isEqualTo(VAL_DATE);
    assertThat(ImmutableRatesProvider.meta().timeSeries().get(test)).isEqualTo(ImmutableMap.of(GBP_USD_WM, ts));
    assertThat(test.toImmutableRatesProvider()).isSameAs(test);
  }
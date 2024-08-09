  @Test
  public void test_parse_standard() {
    CharSource source =
        ResourceLocator.ofClasspath("com/opengamma/strata/loader/csv/sensitivity-standard.csv").getCharSource();

    assertThat(LOADER.isKnownFormat(source)).isTrue();
    ValueWithFailures<ListMultimap<String, CurveSensitivities>> test = LOADER.parse(ImmutableList.of(source));
    assertThat(test.getFailures().size()).as(test.getFailures().toString()).isEqualTo(0);
    assertThat(test.getValue().size()).isEqualTo(1);
    List<CurveSensitivities> list = test.getValue().get("");
    assertThat(list).hasSize(1);

    CurveSensitivities csens0 = list.get(0);
    assertThat(csens0.getTypedSensitivities()).hasSize(2);
    String tenors = "1D, 1W, 2W, 1M, 3M, 6M, 12M, 2Y, 5Y, 10Y";
    assertSens(csens0, ZERO_RATE_DELTA, "GBP", GBP, tenors, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    assertSens(csens0, ZERO_RATE_DELTA, "GBP-LIBOR", GBP, tenors, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    assertSens(csens0, ZERO_RATE_GAMMA, "GBP", GBP, tenors, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1);
    assertSens(csens0, ZERO_RATE_GAMMA, "GBP-LIBOR", GBP, tenors, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1);
  }
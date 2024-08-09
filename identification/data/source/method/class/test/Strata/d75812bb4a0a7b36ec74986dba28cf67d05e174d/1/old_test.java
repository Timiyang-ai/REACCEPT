  @ParameterizedTest
  @MethodSource("data_normalized")
  public void test_normalized(Period period, Period normalized) {
    assertThat(Tenor.of(period).normalized().getPeriod()).isEqualTo(normalized);
  }
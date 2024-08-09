  @ParameterizedTest
  @MethodSource("data_ofMonths")
  public void test_ofMonths(int months, Period stored, String str) {
    assertThat(Tenor.ofMonths(months).getPeriod()).isEqualTo(stored);
    assertThat(Tenor.ofMonths(months).toString()).isEqualTo(str);
  }
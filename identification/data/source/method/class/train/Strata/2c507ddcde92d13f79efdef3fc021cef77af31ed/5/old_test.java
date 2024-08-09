  @ParameterizedTest
  @MethodSource("data_ofYears")
  public void test_ofYears(int years, Period stored, String str) {
    assertThat(Tenor.ofYears(years).getPeriod()).isEqualTo(stored);
    assertThat(Tenor.ofYears(years).toString()).isEqualTo(str);
  }
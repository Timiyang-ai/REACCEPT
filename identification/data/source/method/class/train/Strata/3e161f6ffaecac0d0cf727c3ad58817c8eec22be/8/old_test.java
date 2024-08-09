  @ParameterizedTest
  @MethodSource("data_ofYears")
  public void test_ofYears(int years, Period normalized, String str) {
    assertThat(Frequency.ofYears(years).getPeriod()).isEqualTo(normalized);
    assertThat(Frequency.ofYears(years).toString()).isEqualTo(str);
  }
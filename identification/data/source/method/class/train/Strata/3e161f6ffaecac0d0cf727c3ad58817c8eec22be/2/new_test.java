  @ParameterizedTest
  @MethodSource("data_ofMonths")
  public void test_ofMonths(int months, Period normalized, String str) {
    assertThat(Frequency.ofMonths(months).getPeriod()).isEqualTo(normalized);
    assertThat(Frequency.ofMonths(months).toString()).isEqualTo(str);
  }
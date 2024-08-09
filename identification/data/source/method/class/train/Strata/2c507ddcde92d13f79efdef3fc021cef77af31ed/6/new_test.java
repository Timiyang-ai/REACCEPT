  @ParameterizedTest
  @MethodSource("data_create")
  public void test_parse(Frequency test, Period period, String toString) {
    assertThat(Frequency.parse(toString)).isEqualTo(test);
    assertThat(Frequency.parse(toString).getPeriod()).isEqualTo(period);
  }
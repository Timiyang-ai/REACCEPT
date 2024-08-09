  @ParameterizedTest
  @MethodSource("data_name")
  public void test_toString(FloatingRateType convention, String name) {
    assertThat(convention.toString()).isEqualTo(name);
  }
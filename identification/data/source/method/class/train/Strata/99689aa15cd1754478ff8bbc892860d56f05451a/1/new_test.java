  @ParameterizedTest
  @MethodSource("data_name")
  public void test_toString(PriceIndexCalculationMethod convention, String name) {
    assertThat(convention.toString()).isEqualTo(name);
  }
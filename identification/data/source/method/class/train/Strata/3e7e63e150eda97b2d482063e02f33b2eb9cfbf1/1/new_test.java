  @ParameterizedTest
  @MethodSource("data_name")
  public void test_toString(OvernightIndex convention, String name) {
    assertThat(convention.toString()).isEqualTo(name);
  }
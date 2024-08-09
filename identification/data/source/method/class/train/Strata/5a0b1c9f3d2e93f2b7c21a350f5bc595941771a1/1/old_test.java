  @ParameterizedTest
  @MethodSource("data_name")
  public void test_toString(TermDepositConvention convention, String name) {
    assertThat(convention.toString()).isEqualTo(name);
  }
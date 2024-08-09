  @ParameterizedTest
  @MethodSource("data_name")
  public void test_extendedEnum(CdsConvention convention, String name) {
    CdsConvention.of(name);  // ensures map is populated
    ImmutableMap<String, CdsConvention> map = CdsConvention.extendedEnum().lookupAll();
    assertThat(map.get(name)).isEqualTo(convention);
  }
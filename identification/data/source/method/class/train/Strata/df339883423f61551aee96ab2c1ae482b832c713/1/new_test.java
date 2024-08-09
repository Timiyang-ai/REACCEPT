  @Test
  public void test_extendedEnum() {
    ImmutableMap<String, Measure> map = Measure.extendedEnum().lookupAll();
    assertThat(map).hasSize(0);
  }
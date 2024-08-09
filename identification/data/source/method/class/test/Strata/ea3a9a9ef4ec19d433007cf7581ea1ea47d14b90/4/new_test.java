  @Test
  public void test_notBlank_String_ok() {
    assertThat(ArgChecker.notBlank("OG", "name")).isEqualTo("OG");
  }
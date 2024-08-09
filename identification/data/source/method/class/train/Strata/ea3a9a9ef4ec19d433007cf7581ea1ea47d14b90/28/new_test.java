  @Test
  public void test_notEmpty_String_ok() {
    assertThat(ArgChecker.notEmpty("OG", "name")).isEqualTo("OG");
    assertThat(ArgChecker.notEmpty(" ", "name")).isEqualTo(" ");
  }
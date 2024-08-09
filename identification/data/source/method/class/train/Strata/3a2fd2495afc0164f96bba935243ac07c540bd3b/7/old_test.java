  @Test
  public void test_matches_String_ok() {
    assertThat(ArgChecker.matches(Pattern.compile("[A-Z]+"), "OG", "name")).isEqualTo("OG");
  }
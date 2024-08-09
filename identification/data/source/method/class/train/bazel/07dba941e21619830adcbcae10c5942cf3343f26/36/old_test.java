  @Test
  public void getRuleLanguage() {
    assertThat(TargetUtils.getRuleLanguage("java_binary")).isEqualTo("java");
    assertThat(TargetUtils.getRuleLanguage("foobar")).isEqualTo("foobar");
    assertThat(TargetUtils.getRuleLanguage("")).isEmpty();
  }
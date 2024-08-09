  @Test
  public void replaceAllLiteral() throws Exception {
    assertThat(StringUtilities.replaceAllLiteral("bababa", "ba", "ab")).isEqualTo("ababab");
    assertThat(StringUtilities.replaceAllLiteral("bababa", "ba", "")).isEmpty();
    assertThat(StringUtilities.replaceAllLiteral("bababa", "", "ab")).isEqualTo("bababa");
  }
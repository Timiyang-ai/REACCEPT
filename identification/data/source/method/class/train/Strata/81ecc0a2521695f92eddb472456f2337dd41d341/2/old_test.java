  @Test
  public void test_matchingRegex() {
    assertThat(FpmlPartySelector.matchingRegex(Pattern.compile("a[12]")).selectParties(MAP)).containsExactly("A");
    assertThat(FpmlPartySelector.matchingRegex(Pattern.compile("b")).selectParties(MAP)).containsExactly("B");
    assertThat(FpmlPartySelector.matchingRegex(Pattern.compile("c[0-9]")).selectParties(MAP)).containsExactly("C1", "C2");
    assertThat(FpmlPartySelector.matchingRegex(Pattern.compile("d")).selectParties(MAP)).isEmpty();
  }
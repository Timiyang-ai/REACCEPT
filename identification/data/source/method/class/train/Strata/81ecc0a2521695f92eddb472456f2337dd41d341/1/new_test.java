  @Test
  public void test_matching() {
    assertThat(FpmlPartySelector.matching("a1").selectParties(MAP)).containsExactly("A");
    assertThat(FpmlPartySelector.matching("a2").selectParties(MAP)).containsExactly("A");
    assertThat(FpmlPartySelector.matching("b").selectParties(MAP)).containsExactly("B");
    assertThat(FpmlPartySelector.matching("c").selectParties(MAP)).isEmpty();
  }
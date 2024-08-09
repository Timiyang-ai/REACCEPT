  @Test
  public void andSet() {
    check(TokenSet.andSet(S1, S12), T1);
    check(TokenSet.andSet(S12, S34));
  }
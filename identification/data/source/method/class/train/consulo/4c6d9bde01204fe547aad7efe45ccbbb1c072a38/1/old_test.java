  @Test
  public void orSet() {
    check(TokenSet.orSet(S1, S12, S3), T1, T2, T3);
    check(TokenSet.orSet(S1, S3), T1, T3);
  }
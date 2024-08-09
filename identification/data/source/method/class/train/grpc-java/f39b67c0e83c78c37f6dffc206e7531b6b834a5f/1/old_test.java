  @Test
  public void checkAuthority_failsOnNull() {
    thrown.expect(NullPointerException.class);

    GrpcUtil.checkAuthority(null);
  }
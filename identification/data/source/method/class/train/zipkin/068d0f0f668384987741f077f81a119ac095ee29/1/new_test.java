  @Test(expected = IllegalArgumentException.class)
  public void traceId_emptyInvalid() {
    base.toBuilder().traceId("");
  }
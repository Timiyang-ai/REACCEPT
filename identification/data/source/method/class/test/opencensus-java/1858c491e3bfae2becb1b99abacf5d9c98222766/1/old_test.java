  @Test(expected = NullPointerException.class)
  public void spanBuilderWithRemoteParent_NullName() {
    noopTracer.spanBuilderWithRemoteParent(null, null);
  }
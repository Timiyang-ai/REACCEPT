  @Test(expected = NullPointerException.class)
  public void withSpan_NullSpan() {
    noopTracer.withSpan(null);
  }
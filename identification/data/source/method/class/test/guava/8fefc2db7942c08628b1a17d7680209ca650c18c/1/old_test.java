  public void test_skipNulls_useForNull() {
    Joiner j = Joiner.on("x").skipNulls();
    try {
      j = j.useForNull("y");
      fail();
    } catch (UnsupportedOperationException expected) {
    }
  }
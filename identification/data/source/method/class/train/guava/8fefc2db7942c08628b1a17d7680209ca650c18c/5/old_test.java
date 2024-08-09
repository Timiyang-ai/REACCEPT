  public void test_useForNull_skipNulls() {
    Joiner j = Joiner.on("x").useForNull("y");
    try {
      j = j.skipNulls();
      fail();
    } catch (UnsupportedOperationException expected) {
    }
  }
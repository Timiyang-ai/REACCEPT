  @Test
  public void test_wrap_runnable1() {
    // cannot use assertThrows() here
    try {
      Unchecked.wrap((CheckedRunnable) () -> {
        throw new IOException();
      });
      fail("Expected UncheckedIOException");
    } catch (UncheckedIOException ex) {
      // success
    }
  }
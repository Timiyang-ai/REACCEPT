  @Test
  public void test_runnable_fail1() {
    Runnable a = Unchecked.runnable(() -> {
      throw new IOException();
    });
    assertThatExceptionOfType(UncheckedIOException.class).isThrownBy(() -> a.run());
  }
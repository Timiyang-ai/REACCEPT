  @Test
  public void test_propagate() {
    Error error = new Error("a");
    IllegalArgumentException argEx = new IllegalArgumentException("b");
    IOException ioEx = new IOException("c");
    URISyntaxException namingEx = new URISyntaxException("d", "e");

    // use old-style try-catch to ensure test really working
    try {
      Unchecked.propagate(error);
      fail("Expected Error");
    } catch (Error ex) {
      assertThat(ex).isSameAs(error);
    }
    try {
      Unchecked.propagate(argEx);
      fail("Expected IllegalArgumentException");
    } catch (IllegalArgumentException ex) {
      assertThat(ex).isSameAs(argEx);
    }
    try {
      Unchecked.propagate(ioEx);
      fail("Expected UncheckedIOException");
    } catch (UncheckedIOException ex) {
      assertThat(ex.getClass()).isEqualTo(UncheckedIOException.class);
      assertThat(ex.getCause()).isSameAs(ioEx);
    }
    try {
      Unchecked.propagate(namingEx);
      fail("Expected RuntimeException");
    } catch (RuntimeException ex) {
      assertThat(ex.getClass()).isEqualTo(RuntimeException.class);
      assertThat(ex.getCause()).isSameAs(namingEx);
    }

    try {
      Unchecked.propagate(new InvocationTargetException(error));
      fail("Expected Error");
    } catch (Error ex) {
      assertThat(ex).isSameAs(error);
    }
    try {
      Unchecked.propagate(new InvocationTargetException(argEx));
      fail("Expected IllegalArgumentException");
    } catch (IllegalArgumentException ex) {
      assertThat(ex).isSameAs(argEx);
    }
    try {
      Unchecked.propagate(new InvocationTargetException(ioEx));
      fail("Expected UncheckedIOException");
    } catch (UncheckedIOException ex) {
      assertThat(ex.getClass()).isEqualTo(UncheckedIOException.class);
      assertThat(ex.getCause()).isSameAs(ioEx);
    }
    try {
      Unchecked.propagate(new InvocationTargetException(namingEx));
      fail("Expected RuntimeException");
    } catch (RuntimeException ex) {
      assertThat(ex.getClass()).isEqualTo(RuntimeException.class);
      assertThat(ex.getCause()).isSameAs(namingEx);
    }
  }
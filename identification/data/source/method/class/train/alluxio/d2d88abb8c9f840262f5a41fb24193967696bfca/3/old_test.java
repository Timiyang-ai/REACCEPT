  @Test
  public void defaults() throws IOException {
    DeleteOptions options = DeleteOptions.defaults();

    assertEquals(false, options.isRecursive());
  }
  @Test
  public void defaults() throws IOException {
    OpenOptions options = OpenOptions.defaults();

    assertEquals(0, options.getOffset());
  }
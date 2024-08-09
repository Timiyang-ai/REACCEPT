  @Test
  public void isEven() throws IOException {
    shouldCompileTo("{{isEven 2}}", $, "even");
  }
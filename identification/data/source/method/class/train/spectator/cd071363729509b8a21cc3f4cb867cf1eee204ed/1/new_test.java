  @Test
  public void gzip() throws IOException {
    byte[] data = "foo bar baz".getBytes(StandardCharsets.UTF_8);
    String result = new String(HttpUtils.gunzip(HttpUtils.gzip(data)), StandardCharsets.UTF_8);
    Assertions.assertEquals("foo bar baz", result);
  }
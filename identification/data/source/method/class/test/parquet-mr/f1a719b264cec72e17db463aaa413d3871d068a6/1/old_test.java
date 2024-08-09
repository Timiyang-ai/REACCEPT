  @Test
  public void qualifiedURITest() throws IOException {
    URI uri = this.command.qualifiedURI(FILE_PATH);
    Assert.assertEquals("/var/tmp/test.parquet", uri.getPath());
  }
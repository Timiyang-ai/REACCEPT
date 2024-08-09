@Test
  public void pathToURI() {
    final String path = query(_FILE_PATH_TO_URI.args(PATH1));
    final String uri = Paths.get(PATH1).toUri().toString();
    assertEquals(path, uri);
  }
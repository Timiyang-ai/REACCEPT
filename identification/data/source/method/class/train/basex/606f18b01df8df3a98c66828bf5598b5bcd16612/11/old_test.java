@Test
  public void pathToURI() {
    final String path = query(_FILE_PATH_TO_URI.args(PATH1));
    final String uri = new File(PATH1).toURI().toString();
    assertEquals(path.toLowerCase(Locale.ENGLISH), uri.toLowerCase(Locale.ENGLISH));
  }
@Test
  public void pathToNative() throws IOException {
    assertEquals(norm(new File(PATH1).getCanonicalPath()),
        norm(query(_FILE_PATH_TO_NATIVE.args(PATH1))));
    query(_FILE_PATH_TO_NATIVE.args(PATH + ".." + "/test.xml"),
        new File(PATH + ".." + "/test.xml").getCanonicalPath());
  }
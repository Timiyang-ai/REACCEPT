  @Test
  public void getFilePath() throws IOException {
    String[] paths =
        new String[] {Constants.HEADER + "localhost:19998/dir", "/dir", "dir"};
    String expected = "/dir";
    for (String path : paths) {
      String result = FileSystemShellUtils.getFilePath(path, ConfigurationTestUtils.defaults());
      assertEquals(expected, result);
    }
  }
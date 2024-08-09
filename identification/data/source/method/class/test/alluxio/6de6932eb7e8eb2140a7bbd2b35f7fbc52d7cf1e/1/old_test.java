  @Test
  public void createDir() throws IOException {
    File tempDir = new File(mTestFolder.getRoot(), "tmp");
    FileUtils.createDir(tempDir.getAbsolutePath());
    assertTrue(FileUtils.exists(tempDir.getAbsolutePath()));
    assertTrue(tempDir.delete());
  }
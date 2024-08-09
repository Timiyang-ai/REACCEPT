  @Test
  public void createFile() throws IOException {
    File tempFile = new File(mTestFolder.getRoot(), "tmp");
    FileUtils.createFile(tempFile.getAbsolutePath());
    assertTrue(FileUtils.exists(tempFile.getAbsolutePath()));
    assertTrue(tempFile.delete());
  }
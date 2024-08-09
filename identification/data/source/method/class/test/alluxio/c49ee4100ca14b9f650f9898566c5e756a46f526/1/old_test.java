  @Test
  public void createBlockPath() throws IOException {
    String absolutePath = PathUtils.concatPath(mTestFolder.getRoot(), "tmp", "bar");
    File tempFile = new File(absolutePath);
    FileUtils.createBlockPath(tempFile.getAbsolutePath(), mWorkerDataFolderPerms);
    assertTrue(FileUtils.exists(tempFile.getParent()));
  }
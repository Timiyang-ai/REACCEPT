  @Test
  public void persist() throws Exception {
    String testFilePath = "/testPersist/testFile";
    FileSystemTestUtils.createByteFile(mFileSystem, testFilePath, WritePType.MUST_CACHE, 10);
    assertFalse(mFileSystem.getStatus(new AlluxioURI("/testPersist/testFile")).isPersisted());

    int ret = mFsShell.run("persist", testFilePath);
    Assert.assertEquals(0, ret);
    checkFilePersisted(new AlluxioURI("/testPersist/testFile"), 10);
  }
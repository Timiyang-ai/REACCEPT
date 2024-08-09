  @Test
  public void getPath() throws Exception {
    AlluxioURI rootUri = new AlluxioURI("/");
    long rootId = mFileSystemMaster.getFileId(rootUri);
    assertEquals(rootUri, mFileSystemMaster.getPath(rootId));

    // get non-existent id
    try {
      mFileSystemMaster.getPath(rootId + 1234);
      fail("getPath() for a non-existent id should fail.");
    } catch (FileDoesNotExistException e) {
      // Expected case.
    }
  }
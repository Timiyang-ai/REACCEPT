  @Test
  public void getPersistenceState() throws Exception {
    AlluxioURI rootUri = new AlluxioURI("/");
    long rootId = mFileSystemMaster.getFileId(rootUri);
    assertEquals(PersistenceState.PERSISTED, mFileSystemMaster.getPersistenceState(rootId));

    // get non-existent id
    try {
      mFileSystemMaster.getPersistenceState(rootId + 1234);
      fail("getPath() for a non-existent id should fail.");
    } catch (FileDoesNotExistException e) {
      // Expected case.
    }
  }
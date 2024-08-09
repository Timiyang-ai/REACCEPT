  @Test
  public void getFileInfo() throws Exception {
    createFileWithSingleBlock(NESTED_FILE_URI);
    long fileId;
    FileInfo info;

    fileId = mFileSystemMaster.getFileId(ROOT_URI);
    info = mFileSystemMaster.getFileInfo(fileId);
    assertEquals(ROOT_URI.getPath(), info.getPath());
    assertEquals(ROOT_URI.getPath(),
        mFileSystemMaster.getFileInfo(ROOT_URI, GET_STATUS_CONTEXT).getPath());

    fileId = mFileSystemMaster.getFileId(NESTED_URI);
    info = mFileSystemMaster.getFileInfo(fileId);
    assertEquals(NESTED_URI.getPath(), info.getPath());
    assertEquals(NESTED_URI.getPath(),
        mFileSystemMaster.getFileInfo(NESTED_URI, GET_STATUS_CONTEXT).getPath());

    fileId = mFileSystemMaster.getFileId(NESTED_FILE_URI);
    info = mFileSystemMaster.getFileInfo(fileId);
    assertEquals(NESTED_FILE_URI.getPath(), info.getPath());
    assertEquals(NESTED_FILE_URI.getPath(),
        mFileSystemMaster.getFileInfo(NESTED_FILE_URI, GET_STATUS_CONTEXT).getPath());

    // Test non-existent id.
    try {
      mFileSystemMaster.getFileInfo(fileId + 1234);
      fail("getFileInfo() for a non-existent id should fail.");
    } catch (FileDoesNotExistException e) {
      // Expected case.
    }

    // Test non-existent URIs.
    try {
      mFileSystemMaster.getFileInfo(ROOT_FILE_URI, GET_STATUS_CONTEXT);
      fail("getFileInfo() for a non-existent URI should fail.");
    } catch (FileDoesNotExistException e) {
      // Expected case.
    }
    try {
      mFileSystemMaster.getFileInfo(TEST_URI, GET_STATUS_CONTEXT);
      fail("getFileInfo() for a non-existent URI should fail.");
    } catch (FileDoesNotExistException e) {
      // Expected case.
    }
    try {
      mFileSystemMaster.getFileInfo(NESTED_URI.join("DNE"), GET_STATUS_CONTEXT);
      fail("getFileInfo() for a non-existent URI should fail.");
    } catch (FileDoesNotExistException e) {
      // Expected case.
    }
  }
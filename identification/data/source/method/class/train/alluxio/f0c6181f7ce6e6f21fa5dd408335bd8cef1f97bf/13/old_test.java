  @Test
  public void getFileId() throws Exception {
    createFileWithSingleBlock(NESTED_FILE_URI);

    // These URIs exist.
    assertNotEquals(IdUtils.INVALID_FILE_ID, mFileSystemMaster.getFileId(ROOT_URI));
    assertEquals(ROOT_URI, mFileSystemMaster.getPath(mFileSystemMaster.getFileId(ROOT_URI)));

    assertNotEquals(IdUtils.INVALID_FILE_ID, mFileSystemMaster.getFileId(NESTED_URI));
    assertEquals(NESTED_URI,
        mFileSystemMaster.getPath(mFileSystemMaster.getFileId(NESTED_URI)));

    assertNotEquals(IdUtils.INVALID_FILE_ID, mFileSystemMaster.getFileId(NESTED_FILE_URI));
    assertEquals(NESTED_FILE_URI,
        mFileSystemMaster.getPath(mFileSystemMaster.getFileId(NESTED_FILE_URI)));

    // These URIs do not exist.
    assertEquals(IdUtils.INVALID_FILE_ID, mFileSystemMaster.getFileId(ROOT_FILE_URI));
    assertEquals(IdUtils.INVALID_FILE_ID, mFileSystemMaster.getFileId(TEST_URI));
    assertEquals(IdUtils.INVALID_FILE_ID,
        mFileSystemMaster.getFileId(NESTED_FILE_URI.join("DNE")));
  }
@Test
  public void setAttribute() throws Exception {
    mFileSystemMaster.createFile(NESTED_FILE_URI, mNestedFileOptions);
    FileInfo fileInfo = mFileSystemMaster.getFileInfo(NESTED_FILE_URI, GET_STATUS_OPTIONS);
    assertFalse(fileInfo.isPinned());
    assertEquals(Constants.NO_TTL, fileInfo.getTtl());

    // No State.
    mFileSystemMaster.setAttribute(NESTED_FILE_URI, SetAttributeOptions.defaults());
    fileInfo = mFileSystemMaster.getFileInfo(NESTED_FILE_URI, GET_STATUS_OPTIONS);
    assertFalse(fileInfo.isPinned());
    assertEquals(Constants.NO_TTL, fileInfo.getTtl());

    // Just set pinned flag.
    mFileSystemMaster.setAttribute(NESTED_FILE_URI, SetAttributeOptions.defaults().setPinned(true));
    fileInfo = mFileSystemMaster.getFileInfo(NESTED_FILE_URI, GET_STATUS_OPTIONS);
    assertTrue(fileInfo.isPinned());
    assertEquals(Constants.NO_TTL, fileInfo.getTtl());

    // Both pinned flag and ttl value.
    mFileSystemMaster.setAttribute(NESTED_FILE_URI,
        SetAttributeOptions.defaults().setPinned(false).setTtl(1));
    fileInfo = mFileSystemMaster.getFileInfo(NESTED_FILE_URI, GET_STATUS_OPTIONS);
    assertFalse(fileInfo.isPinned());
    assertEquals(1, fileInfo.getTtl());

    mFileSystemMaster.setAttribute(NESTED_URI, SetAttributeOptions.defaults().setTtl(1));
  }
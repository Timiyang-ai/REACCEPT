  @Test
  public void setAttribute() throws Exception {
    mFileSystemMaster.createFile(NESTED_FILE_URI, mNestedFileContext);
    FileInfo fileInfo = mFileSystemMaster.getFileInfo(NESTED_FILE_URI, GET_STATUS_CONTEXT);
    assertFalse(fileInfo.isPinned());
    assertEquals(Constants.NO_TTL, fileInfo.getTtl());

    // No State.
    mFileSystemMaster.setAttribute(NESTED_FILE_URI, SetAttributeContext.defaults());
    fileInfo = mFileSystemMaster.getFileInfo(NESTED_FILE_URI, GET_STATUS_CONTEXT);
    assertFalse(fileInfo.isPinned());
    assertEquals(Constants.NO_TTL, fileInfo.getTtl());

    // Just set pinned flag.
    mFileSystemMaster.setAttribute(NESTED_FILE_URI,
        SetAttributeContext.mergeFrom(SetAttributePOptions.newBuilder().setPinned(true)));
    fileInfo = mFileSystemMaster.getFileInfo(NESTED_FILE_URI, GET_STATUS_CONTEXT);
    assertTrue(fileInfo.isPinned());
    assertEquals(Constants.NO_TTL, fileInfo.getTtl());

    // Both pinned flag and ttl value.
    mFileSystemMaster.setAttribute(NESTED_FILE_URI,
        SetAttributeContext.mergeFrom(SetAttributePOptions.newBuilder().setPinned(false)
            .setCommonOptions(FileSystemMasterCommonPOptions.newBuilder()
            .setTtl(1))));
    fileInfo = mFileSystemMaster.getFileInfo(NESTED_FILE_URI, GET_STATUS_CONTEXT);
    assertFalse(fileInfo.isPinned());
    assertEquals(1, fileInfo.getTtl());

    mFileSystemMaster.setAttribute(NESTED_URI,
        SetAttributeContext.mergeFrom(SetAttributePOptions.newBuilder()
            .setCommonOptions(FileSystemMasterCommonPOptions.newBuilder()
            .setTtl(1))));
  }
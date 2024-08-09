@Test
  public void setStateTest() throws Exception {
    mFileSystemMaster.create(NESTED_FILE_URI, sNestedFileOptions);
    FileInfo fileInfo = mFileSystemMaster.getFileInfo(NESTED_FILE_URI);
    Assert.assertFalse(fileInfo.isPinned());
    Assert.assertEquals(Constants.NO_TTL, fileInfo.getTtl());

    // No State.
    mFileSystemMaster.setAttribute(NESTED_FILE_URI, SetAttributeOptions.defaults());
    fileInfo = mFileSystemMaster.getFileInfo(NESTED_FILE_URI);
    Assert.assertFalse(fileInfo.isPinned());
    Assert.assertEquals(Constants.NO_TTL, fileInfo.getTtl());

    // Just set pinned flag.
    mFileSystemMaster.setAttribute(NESTED_FILE_URI,
        new SetAttributeOptions.Builder().setPinned(true).build());
    fileInfo = mFileSystemMaster.getFileInfo(NESTED_FILE_URI);
    Assert.assertTrue(fileInfo.isPinned());
    Assert.assertEquals(Constants.NO_TTL, fileInfo.getTtl());

    // Both pinned flag and ttl value.
    mFileSystemMaster.setAttribute(NESTED_FILE_URI,
        new SetAttributeOptions.Builder().setPinned(false).setTtl(1).build());
    fileInfo = mFileSystemMaster.getFileInfo(NESTED_FILE_URI);
    Assert.assertFalse(fileInfo.isPinned());
    Assert.assertEquals(1, fileInfo.getTtl());

    // Set ttl for a directory, raise IllegalArgumentException.
    mThrown.expect(IllegalArgumentException.class);
    mFileSystemMaster.setAttribute(NESTED_URI,
        new SetAttributeOptions.Builder().setTtl(1).build());
  }
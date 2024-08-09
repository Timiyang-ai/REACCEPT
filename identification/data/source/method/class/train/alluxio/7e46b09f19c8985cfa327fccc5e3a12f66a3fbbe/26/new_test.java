  @Test
  public void rename() throws Exception {
    mFileSystemMaster.createFile(NESTED_FILE_URI, mNestedFileContext);

    // try to rename a file to root
    try {
      mFileSystemMaster.rename(NESTED_FILE_URI, ROOT_URI, RenameContext.defaults());
      fail("Renaming to root should fail.");
    } catch (InvalidPathException e) {
      assertEquals(ExceptionMessage.RENAME_CANNOT_BE_TO_ROOT.getMessage(), e.getMessage());
    }

    // move root to another path
    try {
      mFileSystemMaster.rename(ROOT_URI, TEST_URI, RenameContext.defaults());
      fail("Should not be able to rename root");
    } catch (InvalidPathException e) {
      assertEquals(ExceptionMessage.ROOT_CANNOT_BE_RENAMED.getMessage(), e.getMessage());
    }

    // move to existing path
    try {
      mFileSystemMaster.rename(NESTED_FILE_URI, NESTED_URI, RenameContext.defaults());
      fail("Should not be able to overwrite existing file.");
    } catch (FileAlreadyExistsException e) {
      assertEquals(ExceptionMessage.FILE_ALREADY_EXISTS.getMessage(NESTED_URI.getPath()),
          e.getMessage());
    }

    // move a nested file to a root file
    mFileSystemMaster.rename(NESTED_FILE_URI, TEST_URI, RenameContext.defaults());
    assertEquals(mFileSystemMaster.getFileInfo(TEST_URI, GET_STATUS_CONTEXT).getPath(),
        TEST_URI.getPath());

    // move a file where the dst is lexicographically earlier than the source
    AlluxioURI newDst = new AlluxioURI("/abc_test");
    mFileSystemMaster.rename(TEST_URI, newDst, RenameContext.defaults());
    assertEquals(mFileSystemMaster.getFileInfo(newDst, GET_STATUS_CONTEXT).getPath(),
        newDst.getPath());
  }
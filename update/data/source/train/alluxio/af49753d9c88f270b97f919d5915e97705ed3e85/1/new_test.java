@Test
  public void rename() throws Exception {
    mFileSystemMaster.createFile(NESTED_FILE_URI, mNestedFileOptions);

    // try to rename a file to root
    try {
      mFileSystemMaster.rename(NESTED_FILE_URI, ROOT_URI, RenameOptions.defaults());
      Assert.fail("Renaming to root should fail.");
    } catch (InvalidPathException e) {
      Assert.assertEquals(ExceptionMessage.RENAME_CANNOT_BE_TO_ROOT.getMessage(), e.getMessage());
    }

    // move root to another path
    try {
      mFileSystemMaster.rename(ROOT_URI, TEST_URI, RenameOptions.defaults());
      Assert.fail("Should not be able to rename root");
    } catch (InvalidPathException e) {
      Assert.assertEquals(ExceptionMessage.ROOT_CANNOT_BE_RENAMED.getMessage(), e.getMessage());
    }

    // move to existing path
    try {
      mFileSystemMaster.rename(NESTED_FILE_URI, NESTED_URI, RenameOptions.defaults());
      Assert.fail("Should not be able to overwrite existing file.");
    } catch (FileAlreadyExistsException e) {
      Assert.assertEquals(ExceptionMessage.FILE_ALREADY_EXISTS.getMessage(NESTED_URI.getPath()),
          e.getMessage());
    }

    // move a nested file to a root file
    mFileSystemMaster.rename(NESTED_FILE_URI, TEST_URI, RenameOptions.defaults());
    Assert.assertEquals(mFileSystemMaster.getFileInfo(TEST_URI).getPath(), TEST_URI.getPath());

    // move a file where the dst is lexicographically earlier than the source
    AlluxioURI newDst = new AlluxioURI("/abc_test");
    mFileSystemMaster.rename(TEST_URI, newDst, RenameOptions.defaults());
    Assert.assertEquals(mFileSystemMaster.getFileInfo(newDst).getPath(), newDst.getPath());
  }
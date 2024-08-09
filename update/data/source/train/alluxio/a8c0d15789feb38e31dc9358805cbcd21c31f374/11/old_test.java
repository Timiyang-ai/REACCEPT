@Test
  public void renameTest() throws Exception {
    mFileSystemMaster.create(NESTED_FILE_URI, sNestedFileOptions);

    // try to rename a file to root
    try {
      mFileSystemMaster.rename(NESTED_FILE_URI, ROOT_URI);
      Assert.fail("Renaming to root should fail.");
    } catch (Exception e) {
      // Expected
    }

    Assert.assertEquals(1, mCounters.get(MasterSource.RENAME_PATH_OPS).getCount());
    Assert.assertEquals(0, mCounters.get(MasterSource.PATHS_RENAMED).getCount());

    // move a nested file to a root file
    mFileSystemMaster.rename(NESTED_FILE_URI, TEST_URI);

    Assert.assertEquals(2, mCounters.get(MasterSource.RENAME_PATH_OPS).getCount());
    Assert.assertEquals(1, mCounters.get(MasterSource.PATHS_RENAMED).getCount());
  }
@Test
  public void mkdirTest() throws Exception {
    mFileSystemMaster.mkdir(DIRECTORY_URI, CreatePathOptions.defaults());

    Assert.assertEquals(1, mCounters.get(MasterSource.CREATE_DIRECTORY_OPS).getCount());
    Assert.assertEquals(1, mCounters.get(MasterSource.DIRECTORIES_CREATED).getCount());

    // trying to create a directory that already exist
    try {
      mFileSystemMaster.mkdir(DIRECTORY_URI, CreatePathOptions.defaults());
      Assert.fail("create a directory that already exist must throw an exception");
    } catch (FileAlreadyExistsException e) {
      // do nothing
    }

    Assert.assertEquals(2, mCounters.get(MasterSource.CREATE_DIRECTORY_OPS).getCount());
    Assert.assertEquals(1, mCounters.get(MasterSource.DIRECTORIES_CREATED).getCount());
  }
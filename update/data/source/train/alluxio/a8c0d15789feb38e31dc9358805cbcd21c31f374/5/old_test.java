@Test
  public void createFileTest() throws Exception {
    mFileSystemMaster.create(ROOT_FILE_URI, sNestedFileOptions);

    Assert.assertEquals(1, mCounters.get(MasterSource.CREATE_FILE_OPS).getCount());
    Assert.assertEquals(1, mCounters.get(MasterSource.FILES_CREATED).getCount());

    // trying to create a file that already exist
    try {
      mFileSystemMaster.create(ROOT_FILE_URI, sNestedFileOptions);
      Assert.fail("create a file that already exist must throw an eception");
    } catch (FileAlreadyExistsException e) {
      // do nothing
    }

    Assert.assertEquals(2, mCounters.get(MasterSource.CREATE_FILE_OPS).getCount());
    Assert.assertEquals(1, mCounters.get(MasterSource.FILES_CREATED).getCount());

    // create a nested path (i.e. 2 files and 2 directories will be created)
    mFileSystemMaster.create(NESTED_FILE_URI, sNestedFileOptions);

    Assert.assertEquals(3, mCounters.get(MasterSource.CREATE_FILE_OPS).getCount());
    Assert.assertEquals(2, mCounters.get(MasterSource.FILES_CREATED).getCount());
    Assert.assertEquals(0, mCounters.get(MasterSource.CREATE_DIRECTORY_OPS).getCount());
    Assert.assertEquals(2, mCounters.get(MasterSource.DIRECTORIES_CREATED).getCount());
  }
  @Test
  public void getStorageDirs() {
    List<StorageDir> dirs = mTier.getStorageDirs();
    Assert.assertEquals(2, dirs.size());
    Assert.assertEquals(mTestBlockDirPath1, dirs.get(0).getDirPath());
    Assert.assertEquals(mTestBlockDirPath2, dirs.get(1).getDirPath());
  }
  private StorageDir newStorageDir(File testDir) throws Exception {
    return StorageDir.newStorageDir(mTier, TEST_DIR_INDEX, TEST_DIR_CAPACITY,
         testDir.getAbsolutePath(), "MEM");
  }
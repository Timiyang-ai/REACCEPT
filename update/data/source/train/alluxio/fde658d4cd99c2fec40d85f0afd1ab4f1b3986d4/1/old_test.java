@Test
  public void toThriftTest() {
    CreateFileOptions options = CreateFileOptions.defaults();
    CreateFileTOptions thriftOptions = options.toThrift();
    Assert.assertTrue(thriftOptions.isRecursive());
    Assert.assertTrue(thriftOptions.isSetPersisted());
    Assert.assertEquals(mDefaultWriteType.getUnderStorageType().isSyncPersist(), thriftOptions
        .isPersisted());
    Assert.assertEquals(mDefaultBlockSizeBytes, thriftOptions.getBlockSizeBytes());
    Assert.assertEquals(Constants.NO_TTL, thriftOptions.getTtl());
  }
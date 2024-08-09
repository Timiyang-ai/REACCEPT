@Test
  public void toThrift() {
    CreateFileOptions options = CreateFileOptions.defaults();
    CreateFileTOptions thriftOptions = options.toThrift();
    Assert.assertTrue(thriftOptions.isRecursive());
    Assert.assertTrue(thriftOptions.isSetPersisted());
    Assert.assertEquals(mDefaultWriteType.getUnderStorageType().isSyncPersist(), thriftOptions
        .isPersisted());
    Assert.assertEquals(mDefaultBlockSizeBytes, thriftOptions.getBlockSizeBytes());
    Assert.assertEquals(Constants.NO_TTL, thriftOptions.getTtl());
    Assert.assertEquals(alluxio.thrift.TTtlAction.Delete, thriftOptions.getTtlAction());
  }
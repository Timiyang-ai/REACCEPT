@Test
  public void toInStreamOptions() {
    OpenFileOptions options = OpenFileOptions.defaults();
    InStreamOptions inStreamOptions = options.toInStreamOptions();
    Assert.assertEquals(options.getReadType().getAlluxioStorageType(),
        inStreamOptions.getAlluxioStorageType());
    Assert.assertEquals(options.getCacheLocationPolicy(), inStreamOptions.getCacheLocationPolicy());
    Assert.assertEquals(options.getUfsReadLocationPolicy(),
        inStreamOptions.getUfsReadLocationPolicy());
    Assert.assertEquals(options.getMaxUfsReadConcurrency(),
        inStreamOptions.getMaxUfsReadConcurrency());
  }
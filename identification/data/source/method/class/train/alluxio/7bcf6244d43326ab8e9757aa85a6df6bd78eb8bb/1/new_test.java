@Test
  public void toInStreamOptions() {
    OpenFileOptions options = OpenFileOptions.defaults();
    InStreamOptions inStreamOptions = options.toInStreamOptions();
    Assert.assertEquals(options.getReadType().getAlluxioStorageType(),
        inStreamOptions.getAlluxioStorageType());
    Assert.assertEquals(options.getLocationPolicy(), inStreamOptions.getLocationPolicy());
  }
@Test
  public void defaultsTest() throws Exception {
    Configuration conf = new Configuration();
    conf.set(Constants.USER_BLOCK_SIZE_BYTES_DEFAULT, "64MB");
    MasterContext.reset(conf);

    CreateDirectoryOptions options = CreateDirectoryOptions.defaults();

    Assert.assertEquals(false, options.isAllowExists());
    Assert.assertFalse(options.isPersisted());
    Assert.assertFalse(options.isRecursive());
    MasterContext.reset();
  }
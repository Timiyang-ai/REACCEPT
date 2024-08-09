@Test
  public void toThriftTest() {
    MountOptions options = MountOptions.defaults();
    MountTOptions thriftOptions = options.toThrift();
    Assert.assertFalse(thriftOptions.isReadOnly());

    options.setReadOnly(true);
    options.setShared(true);
    thriftOptions = options.toThrift();
    Assert.assertTrue(thriftOptions.isReadOnly());
    Assert.assertTrue(thriftOptions.isShared());
  }
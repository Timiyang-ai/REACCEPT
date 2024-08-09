@Test
  public void toThriftTest() {
    MountOptions options = MountOptions.defaults();
    MountTOptions thriftOptions = options.toThrift();
    Assert.assertFalse(thriftOptions.isReadOnly());

    options.setReadOnly(true);
    thriftOptions = options.toThrift();
    Assert.assertTrue(thriftOptions.isReadOnly());
  }
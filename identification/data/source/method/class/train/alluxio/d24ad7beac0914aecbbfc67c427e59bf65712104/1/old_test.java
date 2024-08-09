@Test
  public void toThriftTest() throws IOException {
    CreateUfsFileOptions options = CreateUfsFileOptions.defaults();
    String owner = "test-owner";
    String group = "test-group";
    short mode = Constants.DEFAULT_FILE_SYSTEM_MODE;

    options.setOwner(owner);
    options.setGroup(group);
    options.setMode(mode);

    CreateUfsFileTOptions thriftOptions = options.toThrift();
    Assert.assertEquals(owner, thriftOptions.getOwner());
    Assert.assertEquals(group, thriftOptions.getGroup());
    Assert.assertEquals(mode, thriftOptions.getMode());
  }
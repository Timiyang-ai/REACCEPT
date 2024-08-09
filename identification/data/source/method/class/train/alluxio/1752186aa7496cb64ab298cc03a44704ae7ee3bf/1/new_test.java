@Test
  public void toThriftTest() throws IOException {
    CompleteUfsFileOptions options = CompleteUfsFileOptions.defaults();
    String owner = "test-owner";
    String group = "test-group";
    short permission = Constants.DEFAULT_FILE_SYSTEM_MODE;

    options.setOwner(owner);
    options.setGroup(group);
    options.setMode(permission);

    CompleteUfsFileTOptions thriftOptions = options.toThrift();
    Assert.assertEquals(owner, thriftOptions.getOwner());
    Assert.assertEquals(group, thriftOptions.getGroup());
    Assert.assertEquals(permission, thriftOptions.getMode());
  }
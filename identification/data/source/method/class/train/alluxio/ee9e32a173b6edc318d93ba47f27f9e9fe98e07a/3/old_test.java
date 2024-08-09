@Test
  public void toThrift() throws IOException {
    CreateUfsFileOptions options = CreateUfsFileOptions.defaults();
    String owner = "test-owner";
    String group = "test-group";
    short mode = Constants.DEFAULT_FILE_SYSTEM_MODE;

    options.setPermission(new Permission(owner, group, mode));

    CreateUfsFileTOptions thriftOptions = options.toThrift();
    Assert.assertEquals(owner, thriftOptions.getOwner());
    Assert.assertEquals(group, thriftOptions.getGroup());
    Assert.assertEquals(mode, thriftOptions.getMode());
  }
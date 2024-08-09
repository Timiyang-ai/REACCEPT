@Test
  public void toThriftTest() throws IOException {
    CompleteUfsFileOptions options = CompleteUfsFileOptions.defaults();
    String user = "test-user";
    String group = "test-group";
    short permission = Constants.DEFAULT_FS_FULL_PERMISSION;

    options.setUser(user);
    options.setGroup(group);
    options.setPermission(permission);

    CompleteUfsFileTOptions thriftOptions = options.toThrift();
    Assert.assertEquals(user, thriftOptions.getUser());
    Assert.assertEquals(group, thriftOptions.getGroup());
    Assert.assertEquals(permission, thriftOptions.getPermission());
  }
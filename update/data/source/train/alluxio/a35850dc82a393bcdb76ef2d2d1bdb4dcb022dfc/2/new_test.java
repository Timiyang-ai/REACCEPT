@Test
  public void defaultsTest() throws IOException {
    ClientContext.getConf().set(Constants.SECURITY_AUTHENTICATION_TYPE, "SIMPLE");
    ClientContext.getConf().set(Constants.SECURITY_LOGIN_USERNAME, "foo");
    // Use IdentityUserGroupMapping to map user "foo" to group "foo".
    ClientContext.getConf().set(Constants.SECURITY_GROUP_MAPPING,
        IdentityUserGroupsMapping.class.getName());

    CompleteUfsFileOptions options = CompleteUfsFileOptions.defaults();

    PermissionStatus expectedPs =
        PermissionStatus.defaults().applyFileUMask(ClientContext.getConf());

    Assert.assertEquals("foo", options.getUser());
    Assert.assertEquals("foo", options.getGroup());
    Assert.assertEquals(expectedPs.getPermission().toShort(), options.getPermission());
    ClientTestUtils.resetClientContext();
  }
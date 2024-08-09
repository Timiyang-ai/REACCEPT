@Test
  public void getGroupFromGrpcClient() throws Exception {
    // When security is not enabled, user and group are not set
    mConfiguration.set(PropertyKey.SECURITY_AUTHENTICATION_TYPE, AuthType.NOSASL.getAuthName());
    Assert.assertEquals("", SecurityUtils.getGroupFromGrpcClient(mConfiguration));

    mConfiguration.set(PropertyKey.SECURITY_AUTHENTICATION_TYPE, AuthType.SIMPLE.getAuthName());
    mConfiguration.set(PropertyKey.SECURITY_GROUP_MAPPING_CLASS,
        IdentityUserGroupsMapping.class.getName());
    AuthenticatedClientUser.set("test_client_user");
    Assert.assertEquals("test_client_user", SecurityUtils.getGroupFromGrpcClient(mConfiguration));
  }
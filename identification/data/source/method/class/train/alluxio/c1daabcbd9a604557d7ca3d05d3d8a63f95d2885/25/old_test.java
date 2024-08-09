@Test
  public void getOwnerFromGrpcClient() throws Exception {
    // When security is not enabled, user and group are not set
    Configuration.set(PropertyKey.SECURITY_AUTHENTICATION_TYPE, AuthType.NOSASL.getAuthName());
    Assert.assertEquals("", SecurityUtils.getOwnerFromGrpcClient());

    Configuration.set(PropertyKey.SECURITY_AUTHENTICATION_TYPE, AuthType.SIMPLE.getAuthName());
    Configuration.set(PropertyKey.SECURITY_GROUP_MAPPING_CLASS,
        IdentityUserGroupsMapping.class.getName());
    AuthenticatedClientUser.set("test_client_user");
    Assert.assertEquals("test_client_user", SecurityUtils.getOwnerFromGrpcClient());
  }
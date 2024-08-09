@Test
  public void getOwnerFromLoginModule() throws Exception {
    // When security is not enabled, user and group are not set
    mConfiguration.set(PropertyKey.SECURITY_AUTHENTICATION_TYPE, AuthType.NOSASL.getAuthName());
    Assert.assertEquals("", SecurityUtils.getOwnerFromLoginModule(mConfiguration));

    // When authentication is enabled, user and group are inferred from login module
    mConfiguration.set(PropertyKey.SECURITY_AUTHENTICATION_TYPE, AuthType.SIMPLE.getAuthName());
    mConfiguration.set(PropertyKey.SECURITY_LOGIN_USERNAME, "test_login_user");
    mConfiguration.set(PropertyKey.SECURITY_GROUP_MAPPING_CLASS,
        IdentityUserGroupsMapping.class.getName());
    Assert.assertEquals("test_login_user", SecurityUtils.getOwnerFromLoginModule(mConfiguration));
  }
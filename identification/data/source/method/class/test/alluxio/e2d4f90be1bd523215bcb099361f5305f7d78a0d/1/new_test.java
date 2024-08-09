@Test
  public void setUserFromLoginModuleTest() throws Exception {
    Configuration.defaultInit();
    Permission permission = Permission.defaults();

    // When security is not enabled, user and group are not set
    Configuration.set(Constants.SECURITY_AUTHENTICATION_TYPE, AuthType.NOSASL.getAuthName());
    permission.setUserFromThriftClient();
    verifyPermission("", "", (short) 0777, permission);

    // When authentication is enabled, user and group are inferred from login module
    Configuration.set(Constants.SECURITY_AUTHENTICATION_TYPE, AuthType.SIMPLE.getAuthName());
    Configuration.set(Constants.SECURITY_LOGIN_USERNAME, "test_login_user");
    Configuration.set(Constants.SECURITY_GROUP_MAPPING, IdentityUserGroupsMapping.class.getName());
    Whitebox.setInternalState(LoginUser.class, "sLoginUser", (String) null);

    permission.setUserFromLoginModule();
    verifyPermission("test_login_user", "test_login_user", (short) 0777, permission);
  }
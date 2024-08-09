@Test
  public void setUserFromLoginModuleTest() throws Exception {
    Configuration conf = new Configuration();
    Permission permission = Permission.defaults();

    // When security is not enabled, user and group are not set
    conf.set(Constants.SECURITY_AUTHENTICATION_TYPE, AuthType.NOSASL.getAuthName());
    permission.setUserFromThriftClient(conf);
    verifyPermission("", "", (short) 0777, permission);

    // When authentication is enabled, user and group are inferred from login module
    conf.set(Constants.SECURITY_AUTHENTICATION_TYPE, AuthType.SIMPLE.getAuthName());
    conf.set(Constants.SECURITY_LOGIN_USERNAME, "test_login_user");
    conf.set(Constants.SECURITY_GROUP_MAPPING, IdentityUserGroupsMapping.class.getName());
    Whitebox.setInternalState(LoginUser.class, "sLoginUser", (String) null);

    permission.setUserFromLoginModule(conf);
    verifyPermission("test_login_user", "test_login_user", (short) 0777, permission);
  }
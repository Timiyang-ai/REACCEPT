@Test
  public void setUserFromThriftClientTest() throws Exception {
    Configuration conf = new Configuration();
    Permission permission = Permission.defaults();

    // When security is not enabled, user and group are not set
    conf.set(Constants.SECURITY_AUTHENTICATION_TYPE, AuthType.NOSASL.getAuthName());
    permission.setUserFromThriftClient(conf);
    verifyPermission("", "", (short) 0777, permission);

    conf.set(Constants.SECURITY_AUTHENTICATION_TYPE, AuthType.SIMPLE.getAuthName());
    conf.set(Constants.SECURITY_GROUP_MAPPING, IdentityUserGroupsMapping.class.getName());
    AuthenticatedClientUser.set("test_client_user");

    // When authentication is enabled, user and group are inferred from thrift transport
    permission.setUserFromThriftClient(conf);
    verifyPermission("test_client_user", "test_client_user", (short) 0777, permission);
  }
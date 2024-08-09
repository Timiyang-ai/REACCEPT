public Permission setUserFromThriftClient(Configuration conf) throws IOException {
    if (!SecurityUtils.isAuthenticationEnabled(conf)) {
      // no authentication, no user to set
      return this;
    }
    // get the username through the authentication mechanism
    User user = AuthenticatedClientUser.get(conf);
    Preconditions.checkNotNull(user, ExceptionMessage.AUTHORIZED_CLIENT_USER_IS_NULL.getMessage());
    mUserName = user.getName();
    mGroupName = CommonUtils.getPrimaryGroupName(conf, user.getName());
    return this;
  }
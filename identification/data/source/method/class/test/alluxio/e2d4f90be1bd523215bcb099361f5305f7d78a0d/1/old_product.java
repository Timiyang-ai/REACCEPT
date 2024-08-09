public Permission setUserFromLoginModule(Configuration conf) throws IOException {
    if (!SecurityUtils.isAuthenticationEnabled(conf)) {
      // no authentication, no user to set
      return this;
    }
    // get the username through the login module
    String loginUserName = LoginUser.get(conf).getName();
    mUserName = loginUserName;
    mGroupName = CommonUtils.getPrimaryGroupName(conf, loginUserName);
    return this;
  }
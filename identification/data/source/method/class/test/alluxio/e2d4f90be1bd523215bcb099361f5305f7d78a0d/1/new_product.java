public Permission setUserFromLoginModule() throws IOException {
    if (!SecurityUtils.isAuthenticationEnabled()) {
      // no authentication, no user to set
      return this;
    }
    // get the username through the login module
    String loginUserName = LoginUser.get().getName();
    mUserName = loginUserName;
    mGroupName = CommonUtils.getPrimaryGroupName(loginUserName);
    return this;
  }
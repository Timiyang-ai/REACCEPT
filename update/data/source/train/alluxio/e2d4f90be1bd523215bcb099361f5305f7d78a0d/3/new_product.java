public static CompleteUfsFileOptions defaults() {
    String user = null;
    if (SecurityUtils.isAuthenticationEnabled(ClientContext.getConf())) {
      try {
        user = LoginUser.get(ClientContext.getConf()).getName();
      } catch (Exception e) {
        // Fall through to system property approach
      }
    }
    if (user == null) { // Set to system user, or null if there is no system user set
      String systemUser = System.getProperty("user.name");
      user = systemUser.isEmpty() ? null : systemUser;
    }
    String group = user;
    return new CompleteUfsFileOptions(user, group);
  }
public static String getOwnerFromLoginModule(AlluxioConfiguration conf) {
    try {
      return LoginUser.get(conf).getName();
    } catch (UnauthenticatedException | UnsupportedOperationException e) {
      return "";
    }
  }
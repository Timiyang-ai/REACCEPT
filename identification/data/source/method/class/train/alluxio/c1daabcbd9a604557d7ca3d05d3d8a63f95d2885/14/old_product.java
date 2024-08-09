public static String getOwnerFromLoginModule() {
    try {
      return LoginUser.get().getName();
    } catch (UnauthenticatedException | UnsupportedOperationException e) {
      return "";
    }
  }
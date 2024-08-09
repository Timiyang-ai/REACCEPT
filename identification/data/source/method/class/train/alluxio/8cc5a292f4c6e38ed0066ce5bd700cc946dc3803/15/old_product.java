public static String getOwnerFromThriftClient() {
    try {
      User user = AuthenticatedClientUser.get();
      if (user == null) {
        return "";
      }
      return user.getName();
    } catch (IOException e) {
      return "";
    }
  }
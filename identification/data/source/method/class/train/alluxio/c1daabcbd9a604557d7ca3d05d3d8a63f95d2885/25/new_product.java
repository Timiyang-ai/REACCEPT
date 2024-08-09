public static String getOwnerFromGrpcClient(AlluxioConfiguration conf) {
    try {
      User user = AuthenticatedClientUser.get(conf);
      if (user == null) {
        return "";
      }
      return user.getName();
    } catch (IOException e) {
      return "";
    }
  }
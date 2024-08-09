public static String getGroupFromGrpcClient() {
    try {
      User user = AuthenticatedClientUser.get();
      if (user == null) {
        return "";
      }
      return CommonUtils.getPrimaryGroupName(user.getName());
    } catch (IOException e) {
      return "";
    }
  }
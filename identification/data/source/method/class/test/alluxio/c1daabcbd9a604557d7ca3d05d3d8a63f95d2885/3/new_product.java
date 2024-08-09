public static String getGroupFromGrpcClient(AlluxioConfiguration conf) {
    try {
      User user = AuthenticatedClientUser.get(conf);
      if (user == null) {
        return "";
      }
      return CommonUtils.getPrimaryGroupName(user.getName(), conf);
    } catch (IOException e) {
      return "";
    }
  }
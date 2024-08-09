private static String getAccountKeyFromFile(Context context, File file) {
    File accountsParentDir = getAccountsParentDirectory(context);
    while (file != null) {
      File parent = file.getParentFile();
      if (parent.equals(accountsParentDir)) {
        return file.getName();
      }
      file = parent;
    }
    return null;
  }
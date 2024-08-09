static File getFilesDir(String accountKey, Context context) {
    Preconditions.checkArgument(!Strings.isNullOrEmpty(accountKey), "accountKey is null or empty!");
    File accountDir = new File(getAccountsParentDirectory(context), accountKey);
    if (!accountDir.exists()) {
      if (!accountDir.mkdirs()) {
        if (Log.isLoggable(TAG, Log.ERROR)) {
          Log.e(TAG, String.format("Failed to create directory %s", accountDir.getAbsolutePath()));
        }
        // TODO(lizlooney): what to do now?
      }
    }
    return accountDir;
  }
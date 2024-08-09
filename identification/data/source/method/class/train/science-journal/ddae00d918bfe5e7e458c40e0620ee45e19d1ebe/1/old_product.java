static String getSharedPreferencesName(String accountKey) {
    Preconditions.checkArgument(!Strings.isNullOrEmpty(accountKey), "accountKey is null or empty!");
    return SHARED_PREFS_NAME_PREFIX
        + SHARED_PREFS_NAME_DELIMITER
        + accountKey
        + SHARED_PREFS_NAME_DELIMITER;
  }
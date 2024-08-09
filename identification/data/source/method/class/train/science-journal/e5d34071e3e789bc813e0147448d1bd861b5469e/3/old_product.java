static String getDatabaseFileName(String accountKey, String dbName) {
    Preconditions.checkArgument(!Strings.isNullOrEmpty(accountKey), "accountKey is null or empty!");
    return DB_NAME_PREFIX + DB_NAME_DELIMITER + accountKey + DB_NAME_DELIMITER + dbName;
  }
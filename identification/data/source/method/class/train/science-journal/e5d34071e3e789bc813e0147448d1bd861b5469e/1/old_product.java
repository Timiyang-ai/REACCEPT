static String getDatabaseFileName(String accountKey, String dbName) {
    Preconditions.checkArgument(!Strings.isNullOrEmpty(accountKey), "accountKey is null or empty!");
    Preconditions.checkArgument(!Strings.isNullOrEmpty(dbName), "dbName is null or empty!");
    Preconditions.checkArgument(
        getAccountKeyFromDatabaseFileName(dbName) == null,
        "dbName must not already be associated with an AppAccount");
    return DB_NAME_PREFIX + DB_NAME_DELIMITER + accountKey + DB_NAME_DELIMITER + dbName;
  }
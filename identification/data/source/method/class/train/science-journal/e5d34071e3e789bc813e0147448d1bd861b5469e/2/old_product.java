static String getDatabaseFileName(String accountName, String dbName) {
    Preconditions.checkArgument(
        !Strings.isNullOrEmpty(accountName), "accountName is null or empty!");
    return DB_NAME_PREFIX
        + DB_NAME_DELIMITER
        + getAccountKey(accountName)
        + DB_NAME_DELIMITER
        + dbName;
  }
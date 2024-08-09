public static String makeAccountKey(String namespace, String accountId) {
    Preconditions.checkArgument(!Strings.isNullOrEmpty(namespace), "namespace is null or empty!");
    Preconditions.checkArgument(
        !namespace.contains(SHARED_PREFS_NAME_DELIMITER),
        "namespace contains illegal character \"%s\" !",
        SHARED_PREFS_NAME_DELIMITER);
    Preconditions.checkArgument(
        !namespace.contains(DB_NAME_DELIMITER),
        "namespace contains illegal character \"%s\" !",
        DB_NAME_DELIMITER);
    Preconditions.checkArgument(
        !namespace.contains(NAMESPACE_DELIMITER),
        "namespace contains illegal character \"%s\" !",
        NAMESPACE_DELIMITER);
    Preconditions.checkArgument(!Strings.isNullOrEmpty(accountId), "accountId is null or empty!");

    return namespace + NAMESPACE_DELIMITER + accountId;
  }
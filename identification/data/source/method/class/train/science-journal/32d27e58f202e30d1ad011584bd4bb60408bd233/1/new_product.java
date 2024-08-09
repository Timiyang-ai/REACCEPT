static Set<String> removeOtherAccounts(Context context, Set<String> accountKeys) {
    Set<String> accountKeysRemoved = new HashSet<>();

    // Remove files for accounts which are no longer present.
    for (File filesDir : getFilesDirsForAllAccounts(context)) {
      String accountKey = getAccountKeyFromFile(context, filesDir);
      if (accountKey != null) {
        if (!accountKeys.contains(accountKey)) {
          accountKeysRemoved.add(accountKey);
          try {
            deleteRecursively(filesDir);
          } catch (IOException e) {
            if (Log.isLoggable(TAG, Log.ERROR)) {
              Log.e(TAG, "Failed to delete account directory.", e);
            }
          }
        }
      }
    }

    // Remove databases for accounts which are no longer present.
    String[] databaseList = context.databaseList();
    for (String databaseFileName : databaseList) {
      String accountKey = getAccountKeyFromDatabaseFileName(databaseFileName);
      if (accountKey != null) {
        if (!accountKeys.contains(accountKey)) {
          accountKeysRemoved.add(accountKey);
          context.deleteDatabase(databaseFileName);
        }
      }
    }

    // Remove preferences for accounts which are no longer present.
    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    for (String prefKey : new HashSet<>(sharedPreferences.getAll().keySet())) {
      String accountKey = getAccountKeyFromPrefKey(prefKey);
      if (accountKey != null) {
        if (!accountKeys.contains(accountKey)) {
          accountKeysRemoved.add(accountKey);
          sharedPreferences.edit().remove(prefKey).apply();
        }
      }
    }

    return accountKeysRemoved;
  }
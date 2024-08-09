static void removeOtherAccounts(Context context, Set<String> accountNames) {
    Set<String> accountKeys = new HashSet<>();
    for (String accountName : accountNames) {
      accountKeys.add(getAccountKey(accountName));
    }

    // Remove files for accounts which are no longer present.
    for (File filesDir : getFilesDirsForAllAccounts(context)) {
      String accountKey = getAccountKeyFromFile(context, filesDir);
      if (accountKey != null) {
        if (!accountKeys.contains(accountKey)) {
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

    // TODO(lizlooney): remove databases for accounts which are no longer present.

    // Remove preferences for accounts which are no longer present.
    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    for (String prefKey : new HashSet<>(sharedPreferences.getAll().keySet())) {
      String accountKey = getAccountKeyFromPrefKey(prefKey);
      if (accountKey != null) {
        if (!accountKeys.contains(accountKey)) {
          sharedPreferences.edit().remove(prefKey).apply();
        }
      }
    }
  }
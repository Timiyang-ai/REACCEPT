static void removeOtherAccounts(Context context, Set<String> accountKeys) {
    // Remove files for accounts which are no longer present.
    for (File filesDir : getFilesDirsForAllAccounts(context)) {
      String accountKey = getAccountKeyFromFile(context, filesDir);
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

    // Remove databases for accounts which are no longer present.
    String[] databaseList = context.databaseList();
    for (String databaseFileName : databaseList) {
      String accountKey = getAccountKeyFromDatabaseFileName(databaseFileName);
      if (accountKey != null) {
        if (!accountKeys.contains(accountKey)) {
          context.deleteDatabase(databaseFileName);
        }
      }
    }

    // Remove preferences for accounts which are no longer present.
    for (File sharedPreferencesFile : getSharedPreferencesFilesForAllAccounts(context)) {
      String accountKey =
          getAccountKeyFromSharedPreferencesFileName(sharedPreferencesFile.getName());
      if (!accountKeys.contains(accountKey)) {
        // Clear the SharedPreferences.
        SharedPreferences sharedPreferences =
            getSharedPreferences(context, getSharedPreferencesName(sharedPreferencesFile));
        sharedPreferences.edit().clear().commit();
        // Delete the SharedPreferences file.
        sharedPreferencesFile.delete();
      }
    }
  }
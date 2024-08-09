static void removeOtherAccounts(Context context, Set<String> accountKeys) {
    Set<String> missingAccountKeys = new HashSet<>();

    // Remove files for accounts which are no longer present.
    for (File filesDir : getFilesDirsForAllAccounts(context)) {
      String accountKey = getAccountKeyFromFile(context, filesDir);
      if (!accountKeys.contains(accountKey)) {
        missingAccountKeys.add(accountKey);
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
          missingAccountKeys.add(accountKey);
          context.deleteDatabase(databaseFileName);
        }
      }
    }

    // Remove preferences for accounts which are no longer present.
    for (File sharedPreferencesFile : getSharedPreferencesFilesForAllAccounts(context)) {
      String accountKey =
          getAccountKeyFromSharedPreferencesFileName(sharedPreferencesFile.getName());
      if (!accountKeys.contains(accountKey)) {
        missingAccountKeys.add(accountKey);
        // Clear the SharedPreferences.
        SharedPreferences sharedPreferences =
            getSharedPreferences(context, getSharedPreferencesName(sharedPreferencesFile));
        sharedPreferences.edit().clear().commit();
        // Delete the SharedPreferences file.
        sharedPreferencesFile.delete();
      }
    }

    if (!missingAccountKeys.isEmpty()) {
      UsageTracker usageTracker = WhistlePunkApplication.getUsageTracker(context);
      for (int i = 0; i < missingAccountKeys.size(); i++) {
        usageTracker.trackEvent(
            TrackerConstants.CATEGORY_SIGN_IN, TrackerConstants.ACTION_REMOVED_ACCOUNT, null, 0);
      }
    }
  }
  @Test
  public void removeOtherAccounts() throws Exception {
    // Setup - For each of three accounts, create files representing 5 experiments and set a
    // preference.
    for (String accountKey : ImmutableSet.of(ACCOUNT_KEY_1, ACCOUNT_KEY_2, ACCOUNT_KEY_3)) {
      // Create experiment files.
      File accountFilesDir = AccountsUtils.getFilesDir(accountKey, context);
      File experimentsDir = new File(accountFilesDir, "experiments");
      for (int i = 1; i <= 5; i++) {
        String experimentId = "experiment_id_" + i;
        File experimentDir = new File(experimentsDir, experimentId);
        experimentDir.mkdirs();
        Files.touch(new File(experimentDir, "experiment.proto"));
      }
      assertThat(accountFilesDir.list()).hasLength(1);
      assertThat(experimentsDir.list()).hasLength(5);

      // Create a database.
      String dbName = AccountsUtils.getDatabaseFileName(accountKey, "sensors.db");
      SQLiteOpenHelper dbHelper =
          new SQLiteOpenHelper(context, dbName, null, 1) {
            @Override
            public void onCreate(SQLiteDatabase db) {}

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
          };
      SQLiteDatabase db = dbHelper.getReadableDatabase();
      assertThat(Arrays.asList(context.databaseList())).contains(dbName);

      // Set a preference.
      String sharedPreferencesName = AccountsUtils.getSharedPreferencesName(accountKey);
      SharedPreferences sharedPreferences = context.getSharedPreferences(sharedPreferencesName, 0);
      sharedPreferences.edit().putBoolean(KEY_DEFAULT_EXPERIMENT_CREATED, true).commit();
      assertThat(sharedPreferences.contains(KEY_DEFAULT_EXPERIMENT_CREATED)).isTrue();
    }

    // Verify that all the account files exist.
    File account1FilesDir = AccountsUtils.getFilesDir(ACCOUNT_KEY_1, context);
    assertThat(account1FilesDir.exists()).isTrue();
    File account2FilesDir = AccountsUtils.getFilesDir(ACCOUNT_KEY_2, context);
    assertThat(account2FilesDir.exists()).isTrue();
    File account3FilesDir = AccountsUtils.getFilesDir(ACCOUNT_KEY_3, context);
    assertThat(account3FilesDir.exists()).isTrue();
    File account1SharedPreferencesFile =
        getSharedPreferencesFile(AccountsUtils.getSharedPreferencesName(ACCOUNT_KEY_1));
    assertThat(account1SharedPreferencesFile.exists()).isTrue();
    File account2SharedPreferencesFile =
        getSharedPreferencesFile(AccountsUtils.getSharedPreferencesName(ACCOUNT_KEY_2));
    assertThat(account2SharedPreferencesFile.exists()).isTrue();
    File account3SharedPreferencesFile =
        getSharedPreferencesFile(AccountsUtils.getSharedPreferencesName(ACCOUNT_KEY_3));
    assertThat(account3SharedPreferencesFile.exists()).isTrue();

    // Remove accounts other than accounts 2 and 3.
    AccountsUtils.removeOtherAccounts(context, ImmutableSet.of(ACCOUNT_KEY_2, ACCOUNT_KEY_3));

    // Directory for account 1 no longer exists.
    assertThat(account1FilesDir.exists()).isFalse();
    // Database for account 1 no longer exists.
    assertThat(Arrays.asList(context.databaseList()))
        .doesNotContain(AccountsUtils.getDatabaseFileName(ACCOUNT_KEY_1, "sensors.db"));
    // SharedPreferences file for account 1 no longer exists.
    assertThat(account1SharedPreferencesFile.exists()).isFalse();
    // If we re-create the SharedPreferences, KEY_DEFAULT_EXPERIMENT_CREATED is not there.
    SharedPreferences account1SharedPreferences =
        context.getSharedPreferences(AccountsUtils.getSharedPreferencesName(ACCOUNT_KEY_1), 0);
    assertThat(account1SharedPreferences.contains(KEY_DEFAULT_EXPERIMENT_CREATED)).isFalse();
    assertThat(account1SharedPreferences.getBoolean(KEY_DEFAULT_EXPERIMENT_CREATED, false))
        .isFalse();

    // Directories for accounts 2 and 3 still have 5 experiments each.
    assertThat(account2FilesDir.list()).hasLength(1);
    assertThat(new File(account2FilesDir, "experiments").list()).hasLength(5);
    assertThat(account3FilesDir.list()).hasLength(1);
    assertThat(new File(account3FilesDir, "experiments").list()).hasLength(5);
    // Databases for account 2 and 3 still exist.
    assertThat(Arrays.asList(context.databaseList()))
        .contains(AccountsUtils.getDatabaseFileName(ACCOUNT_KEY_2, "sensors.db"));
    assertThat(Arrays.asList(context.databaseList()))
        .contains(AccountsUtils.getDatabaseFileName(ACCOUNT_KEY_3, "sensors.db"));
    // SharedPreferences for accounts 2 and 3 still there.
    assertThat(account2SharedPreferencesFile.exists()).isTrue();
    assertThat(account3SharedPreferencesFile.exists()).isTrue();
    SharedPreferences account2SharedPreferences =
        context.getSharedPreferences(AccountsUtils.getSharedPreferencesName(ACCOUNT_KEY_2), 0);
    assertThat(account2SharedPreferences.contains(KEY_DEFAULT_EXPERIMENT_CREATED)).isTrue();
    assertThat(account2SharedPreferences.getBoolean(KEY_DEFAULT_EXPERIMENT_CREATED, false))
        .isTrue();
    SharedPreferences account3SharedPreferences =
        context.getSharedPreferences(AccountsUtils.getSharedPreferencesName(ACCOUNT_KEY_3), 0);
    assertThat(account3SharedPreferences.contains(KEY_DEFAULT_EXPERIMENT_CREATED)).isTrue();
    assertThat(account3SharedPreferences.getBoolean(KEY_DEFAULT_EXPERIMENT_CREATED, false))
        .isTrue();
  }
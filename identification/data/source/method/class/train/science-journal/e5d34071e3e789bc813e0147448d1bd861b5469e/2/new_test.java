  @Test
  public void getDatabaseFileName() throws Exception {
    String account1DatabaseFileName = AccountsUtils.getDatabaseFileName(ACCOUNT_KEY_1, "runs");
    assertThat(account1DatabaseFileName).startsWith("account_");
    assertThat(account1DatabaseFileName).endsWith("_runs");

    // The database file name for a different account should be different.
    String account2DatabaseFileName = AccountsUtils.getDatabaseFileName(ACCOUNT_KEY_2, "runs");
    assertThat(account1DatabaseFileName).isNotEqualTo(account2DatabaseFileName);

    // Check that accountKey can't be null or empty.
    assertThrows(
        IllegalArgumentException.class, () -> AccountsUtils.getDatabaseFileName(null, "runs"));
    assertThrows(
        IllegalArgumentException.class, () -> AccountsUtils.getDatabaseFileName("", "runs"));
    // Check that dbName can't be null or empty.
    assertThrows(
        IllegalArgumentException.class,
        () -> AccountsUtils.getDatabaseFileName(ACCOUNT_KEY_1, null));
    assertThrows(
        IllegalArgumentException.class, () -> AccountsUtils.getDatabaseFileName(ACCOUNT_KEY_1, ""));
    // Check that dbName can't already be associated with an account.
    assertThrows(
        IllegalArgumentException.class,
        () -> AccountsUtils.getDatabaseFileName(ACCOUNT_KEY_1, account1DatabaseFileName));
  }
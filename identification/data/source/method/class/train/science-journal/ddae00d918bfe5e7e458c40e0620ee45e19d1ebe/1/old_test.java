  @Test
  public void getSharedPreferencesName() throws Exception {
    String account1SharedPreferencesName = AccountsUtils.getSharedPreferencesName(ACCOUNT_KEY_1);
    assertThat(account1SharedPreferencesName).startsWith("account_");
    assertThat(account1SharedPreferencesName).endsWith("_");

    // The SharedPreferences name for a different account should be different.
    String account2SharedPreferencesName = AccountsUtils.getSharedPreferencesName(ACCOUNT_KEY_2);
    assertThat(account1SharedPreferencesName).isNotEqualTo(account2SharedPreferencesName);

    // Check that accountKey can't be null or empty.
    assertThrows(
        IllegalArgumentException.class, () -> AccountsUtils.getSharedPreferencesName(null));
    assertThrows(IllegalArgumentException.class, () -> AccountsUtils.getSharedPreferencesName(""));
  }
  @Test
  public void getFilesDir() throws Exception {
    File account1FilesDir = AccountsUtils.getFilesDir(ACCOUNT_KEY_1, context);
    assertThat(account1FilesDir.exists()).isTrue();
    assertThat(account1FilesDir.isDirectory()).isTrue();
    assertThat(account1FilesDir.getParent()).isEqualTo(context.getFilesDir() + "/accounts");

    // The files directory for a different account should be different.
    File account2FilesDir = AccountsUtils.getFilesDir(ACCOUNT_KEY_2, context);
    assertThat(account1FilesDir).isNotEqualTo(account2FilesDir);
  }
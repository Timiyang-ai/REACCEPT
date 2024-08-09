  @Test
  public void getAccountKeyFromFile() throws Exception {
    File account1FilesDir = AccountsUtils.getFilesDir(ACCOUNT_KEY_1, context);
    assertThat(AccountsUtils.getAccountKeyFromFile(context, account1FilesDir))
        .isEqualTo(ACCOUNT_KEY_1);
  }
  @Test
  public void getAccountType() {
    Assert.assertEquals(account.getAccountTypeByString("Normal"), account.getAccountType());
  }
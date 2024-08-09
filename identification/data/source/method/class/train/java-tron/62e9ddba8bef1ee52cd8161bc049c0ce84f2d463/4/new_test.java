  @Test
  public void setAssets() {
    List<Account> assets = new ArrayList<>();
    Account account = new Account();
    assets.add(account);
    genesisBlock.setAssets(assets);
    Assert.assertEquals(1, genesisBlock.getAssets().size());
  }
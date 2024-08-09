@Test
  public void testGetAccountContainer() {
    AccountService accountService = new InMemAccountService(false, false);
    // Both accountId and containerId are not tracked by AccountService.
    Pair<Account, Container> accountContainer =
        RouterUtils.getAccountContainer(accountService, Account.UNKNOWN_ACCOUNT_ID, Container.UNKNOWN_CONTAINER_ID);
    Assert.assertEquals("Account should be null", null, accountContainer.getFirst());
    Assert.assertEquals("Container should be null", null, accountContainer.getSecond());

    accountContainer =
        RouterUtils.getAccountContainer(accountService, Utils.getRandomShort(random), Utils.getRandomShort(random));
    Assert.assertEquals("Account should be null", null, accountContainer.getFirst());
    Assert.assertEquals("Container should be null", null, accountContainer.getSecond());

    // accountId is tracked by AccountService but containerId not.
    short accountId = Utils.getRandomShort(random);
    short containerId = Utils.getRandomShort(random);
    Account account = new AccountBuilder(accountId, "AccountNameOf" + accountId, Account.AccountStatus.ACTIVE).build();
    accountService.updateAccounts(Arrays.asList(account));
    accountContainer = RouterUtils.getAccountContainer(accountService, accountId, containerId);
    Assert.assertEquals("Account doesn't match", account, accountContainer.getFirst());
    Assert.assertEquals("Container should be null", null, accountContainer.getSecond());

    // Both accountId and containerId are tracked by AccountService.
    Container container =
        new ContainerBuilder(containerId, "ContainerNameOf" + containerId, Container.ContainerStatus.ACTIVE,
            "description", accountId).build();
    account =
        new AccountBuilder(accountId, "AccountNameOf" + accountId, Account.AccountStatus.ACTIVE).addOrUpdateContainer(
            container).build();
    accountService.updateAccounts(Arrays.asList(account));
    accountContainer = RouterUtils.getAccountContainer(accountService, accountId, containerId);
    Assert.assertEquals("Account doesn't match", account, accountContainer.getFirst());
    Assert.assertEquals("Container doesn't match", container, accountContainer.getSecond());
  }
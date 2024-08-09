@Test
  public void testGetAccountContainerName() {
    AccountService accountService = new InMemAccountService(false, false);
    // Both accountId and containerId are not tracked by AccountService.
    Pair<String, String> accountContainerName =
        RouterUtils.getAccountContainerName(accountService, Account.UNKNOWN_ACCOUNT_ID, Container.UNKNOWN_CONTAINER_ID);
    Assert.assertEquals("Account name should be null", null, accountContainerName.getFirst());
    Assert.assertEquals("Container name should be null", null, accountContainerName.getSecond());

    accountContainerName =
        RouterUtils.getAccountContainerName(accountService, Utils.getRandomShort(random), Utils.getRandomShort(random));
    Assert.assertEquals("Account name should be null", null, accountContainerName.getFirst());
    Assert.assertEquals("Container name should be null", null, accountContainerName.getSecond());

    // accountId is tracked by AccountService but containerId not.
    short accountId = Utils.getRandomShort(random);
    short containerId = Utils.getRandomShort(random);
    Account account = new AccountBuilder(accountId, "AccountNameOf" + accountId, Account.AccountStatus.ACTIVE).build();
    accountService.updateAccounts(Arrays.asList(account));
    accountContainerName = RouterUtils.getAccountContainerName(accountService, accountId, containerId);
    Assert.assertEquals("Account name doesn't match", "AccountNameOf" + accountId, accountContainerName.getFirst());
    Assert.assertEquals("Container name should be null", null, accountContainerName.getSecond());

    // Both accountId and containerId are tracked by AccountService.
    Container container =
        new ContainerBuilder(containerId, "ContainerNameOf" + containerId, Container.ContainerStatus.ACTIVE,
            "description", accountId).build();
    account =
        new AccountBuilder(accountId, "AccountNameOf" + accountId, Account.AccountStatus.ACTIVE).addOrUpdateContainer(
            container).build();
    accountService.updateAccounts(Arrays.asList(account));
    accountContainerName = RouterUtils.getAccountContainerName(accountService, accountId, containerId);
    Assert.assertEquals("Account name doesn't match", "AccountNameOf" + accountId, accountContainerName.getFirst());
    Assert.assertEquals("Container name doesn't match", "ContainerNameOf" + containerId,
        accountContainerName.getSecond());
  }
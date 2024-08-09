static Pair<Account, Container> getAccountContainer(AccountService accountService, short accountId,
      short containerId) {
    Account account = accountService.getAccountById(accountId);
    Container container = account == null ? null : account.getContainerById(containerId);
    return new Pair<>(account, container);
  }
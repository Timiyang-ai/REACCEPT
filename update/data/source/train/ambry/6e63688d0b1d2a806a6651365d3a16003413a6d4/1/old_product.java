static Pair<String, String> getAccountContainerName(AccountService accountService, short accountId,
      short containerId) {
    Account account = accountService.getAccountById(accountId);
    String accountName = account == null ? null : account.getName();
    Container container = account == null ? null : account.getContainerById(containerId);
    String containerName = container == null ? null : container.getName();
    return new Pair<>(accountName, containerName);
  }
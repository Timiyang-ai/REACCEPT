public void init() {
    accountStore = AccountStore.create("account");
    transactionStore = TransactionStore.create("trans");
  }
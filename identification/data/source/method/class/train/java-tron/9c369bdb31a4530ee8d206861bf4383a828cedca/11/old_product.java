public void init() {
    this.setAccountStore(AccountStore.create("account"));
    this.setTransactionStore(TransactionStore.create("trans"));
    this.setBlockStore(BlockStore.create("block"));
    this.setWitnessStore(WitnessStore.create("witness"));

    initOther();
  }
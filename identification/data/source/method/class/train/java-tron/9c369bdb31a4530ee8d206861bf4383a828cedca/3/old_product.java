public void init() {
    revokingStore = RevokingStore.getInstance();
    revokingStore.disable();
    this.setAccountStore(AccountStore.create("account"));
    this.setTransactionStore(TransactionStore.create("trans"));
    this.setBlockStore(BlockStore.create("block"));
    this.setUtxoStore(UtxoStore.create("utxo"));
    this.setWitnessStore(WitnessStore.create("witness"));
    this.setAssetIssueStore(AssetIssueStore.create("asset-issue"));
    this.setDynamicPropertiesStore(DynamicPropertiesStore.create("properties"));
    this.setWitnessController(WitnessController.createInstance(this));
    this.setBlockIndexStore(BlockIndexStore.create("block-index"));
    this.khaosDb = new KhaosDatabase("block" + "_KDB");
    this.pendingTransactions = new ArrayList<>();
    this.initGenesis();
    this.witnessController.initWits();
    this.khaosDb.start(genesisBlock);
    revokingStore.enable();
  }
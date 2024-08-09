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
    try {
      this.khaosDb.start(getBlockById(getDynamicPropertiesStore().getLatestBlockHeaderHash()));
    } catch (ItemNotFoundException e) {
      logger.error("Can not find Dynamic highest block from DB! \nnumber={} \nhash={}",
          getDynamicPropertiesStore().getLatestBlockHeaderNumber(),
          getDynamicPropertiesStore().getLatestBlockHeaderHash());
      logger.error("Please delete database directory({}) and restart",
          Args.getInstance().getOutputDirectory());
      System.exit(1);
    } catch (BadItemException e) {
      e.printStackTrace();
      logger.error("DB data broken!");
      logger.error("Please delete database directory({}) and restart",
          Args.getInstance().getOutputDirectory());
      System.exit(1);
    }
    revokingStore.enable();
  }
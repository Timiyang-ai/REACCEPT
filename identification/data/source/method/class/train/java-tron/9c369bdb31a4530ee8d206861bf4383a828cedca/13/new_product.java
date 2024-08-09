public void init() {
    setAccountStore(AccountStore.create("account"));
    setTransactionStore(TransactionStore.create("trans"));
    setBlockStore(BlockStore.create("block"));
    setUtxoStore(UtxoStore.create("utxo"));
    setWitnessStore(WitnessStore.create("witness"));
    setDynamicPropertiesStore(DynamicPropertiesStore.create("properties"));

    numHashCache = new LevelDbDataSourceImpl(
        Args.getInstance().getOutputDirectory(), "block" + "_NUM_HASH");
    numHashCache.initDB();
    khaosDb = new KhaosDatabase("block" + "_KDB");

    pendingTrxs = new ArrayList<>();
    initGenesis();
    blockStore.initHeadBlock(
        Sha256Hash.wrap(this.dynamicPropertiesStore.getLatestBlockHeaderHash()));
  }
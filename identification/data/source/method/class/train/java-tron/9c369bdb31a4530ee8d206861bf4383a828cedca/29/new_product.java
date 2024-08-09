public void init() {
    this.setAccountStore(AccountStore.create("account"));
    this.setTransactionStore(TransactionStore.create("trans"));
    this.setBlockStore(BlockStore.create("block"));
    this.setUtxoStore(UtxoStore.create("utxo"));
    this.setWitnessStore(WitnessStore.create("witness"));
    this.setDynamicPropertiesStore(DynamicPropertiesStore.create("properties"));

    this.numHashCache = new LevelDbDataSourceImpl(
        Args.getInstance().getOutputDirectory(), "block" + "_NUM_HASH");
    this.numHashCache.initDB();
    this.khaosDb = new KhaosDatabase("block" + "_KDB");

    this.pendingTrxs = new ArrayList<>();
    try {
      this.initGenesis();
    } catch (ContractValidateException e) {
      logger.error(e.getMessage());
      System.exit(-1);
    } catch (ContractExeException e) {
      logger.error(e.getMessage());
      System.exit(-1);
    } catch (ValidateSignatureException e) {
      logger.error(e.getMessage());
      System.exit(-1);
    }
    this.initHeadBlock(Sha256Hash.wrap(this.dynamicPropertiesStore.getLatestBlockHeaderHash()));

    revokingStore = RevokingStore.getInstance();
    revokingStore.enable();
  }
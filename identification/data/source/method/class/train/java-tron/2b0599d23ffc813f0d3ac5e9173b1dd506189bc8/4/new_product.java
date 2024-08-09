public void init(BlockCapsule blockCap, boolean eventPluginLoaded) {
    txStartTimeInMs = System.currentTimeMillis();
    DepositImpl deposit = DepositImpl.createRoot(dbManager);
    //load config
    VMConfig config = VMConfigLoader.getInstance().loadCached();
    config.setEventPluginLoaded(eventPluginLoaded);
    //load runner
    runner = TxRunnerRouter.getInstance().route(this, blockCap, deposit, config);
  }
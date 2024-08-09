public void init(BlockCapsule blockCap, boolean eventPluginLoaded) {

    txStartTimeInMs = System.currentTimeMillis();
    //create storage
    Deposit deposit = DepositImpl.createRoot(dbManager);
    //load config
    VMConfig config = VMConfigLoader.getInstance().setSource(dbManager.getDynamicPropertiesStore()).loadNew();
    config.setEventPluginLoaded(eventPluginLoaded);
    //load runner
    runner = TxRunnerRouter.getInstance().route(this, blockCap, deposit, config);
  }
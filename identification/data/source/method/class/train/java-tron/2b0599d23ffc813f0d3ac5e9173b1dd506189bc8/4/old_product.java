public void init(BlockCapsule blockCap, boolean eventPluginLoaded) {
    txStartTimeInMs = System.currentTimeMillis();
    DepositImpl deposit = DepositImpl.createRoot(dbManager);
    runtime = new RuntimeImpl(this, blockCap, deposit, new ProgramInvokeFactoryImpl());
    runtime.setEnableEventLinstener(eventPluginLoaded);
  }
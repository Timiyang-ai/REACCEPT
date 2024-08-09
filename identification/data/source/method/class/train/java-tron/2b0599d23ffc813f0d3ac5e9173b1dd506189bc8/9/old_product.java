public void init(BlockCapsule blockCap) {
    txStartTimeInMs = System.currentTimeMillis();
    DepositImpl deposit = DepositImpl.createRoot(dbManager);
    runtime = new RuntimeImpl(this, blockCap, deposit, new ProgramInvokeFactoryImpl());
  }
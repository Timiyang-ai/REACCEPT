public void init(BlockCapsule blockCap, boolean eventPluginLoaded, boolean vm2) {
    txStartTimeInMs = System.currentTimeMillis();
    transactionContext = new TransactionContext(blockCap, trx, storeFactory, false,
        eventPluginLoaded);
  }
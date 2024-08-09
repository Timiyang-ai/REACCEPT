public synchronized void reportLostFile(final String path) throws IOException, AlluxioException {
    retryRPC(new RpcCallableThrowsTachyonTException<Void>() {
      @Override
      public Void call() throws AlluxioTException, TException {
        mClient.reportLostFile(path);
        return null;
      }
    });
  }
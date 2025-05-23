public synchronized void sessionHeartbeat() throws ConnectionFailedException, IOException {
    retryRPC(new RpcCallable<Void>() {
      @Override
      public Void call() throws TException {
        mClient.sessionHeartbeat(mSessionId, mClientMetrics.getHeartbeatData());
        return null;
      }
    });
  }
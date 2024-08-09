public synchronized void free(final AlluxioURI path, final FreeOptions options)
      throws IOException, AlluxioException {
    retryRPC(new RpcCallableThrowsAlluxioTException<Void>() {
      @Override
      public Void call() throws AlluxioTException, TException {
        mClient.free(path.getPath(), options.isRecursive(), options.toThrift());
        return null;
      }
    });
  }
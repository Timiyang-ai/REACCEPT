@Override
  public long createUfsFile(final long sessionId, final String ufsUri,
      final CreateUfsFileTOptions options) throws AlluxioTException, ThriftIOException {
    return RpcUtils.call(new RpcUtils.RpcCallableThrowsIOException<Long>() {
      @Override
      public Long call() throws AlluxioException, IOException {
        return mWorker
            .createUfsFile(sessionId, new AlluxioURI(ufsUri), new CreateUfsFileOptions(options));
      }
    });
  }
@Override
  public long createUfsFile(final long sessionId, final String ufsUri,
      final CreateUfsFileTOptions options) throws AlluxioTException, ThriftIOException {
    return RpcUtils.call(new RpcUtils.RpcCallableThrowsIOException<Long>() {
      @Override
      public Long call() throws AlluxioException, IOException {
        String user = options.isSetOwner() ? options.getOwner() : "";
        String group = options.isSetGroup() ? options.getGroup() : "";
        short mode = options.isSetMode() ? options.getMode() : Constants.INVALID_MODE;
        return mWorker
            .createUfsFile(sessionId, new AlluxioURI(ufsUri), new Permission(user, group, mode));
      }
    });
  }
public synchronized URIStatus getStatus(final TachyonURI path) throws IOException,
      TachyonException {
    return retryRPC(new RpcCallableThrowsTachyonTException<URIStatus>() {
      @Override
      public URIStatus call() throws TachyonTException, TException {
        return new URIStatus(ThriftUtils.fromThrift(mClient.getStatus(path.getPath())));
      }
    });
  }
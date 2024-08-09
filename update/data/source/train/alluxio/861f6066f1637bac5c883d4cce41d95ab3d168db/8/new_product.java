public synchronized URIStatus getStatus(final AlluxioURI path) throws IOException,
      AlluxioException {
    return retryRPC(new RpcCallableThrowsTachyonTException<URIStatus>() {
      @Override
      public URIStatus call() throws AlluxioTException, TException {
        return new URIStatus(ThriftUtils.fromThrift(mClient.getStatus(path.getPath())));
      }
    });
  }
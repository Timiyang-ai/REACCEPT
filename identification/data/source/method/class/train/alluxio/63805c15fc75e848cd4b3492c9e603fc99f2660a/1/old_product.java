public synchronized List<URIStatus> listStatus(final AlluxioURI path)
      throws IOException, AlluxioException {
    return retryRPC(new RpcCallableThrowsAlluxioTException<List<URIStatus>>() {
      @Override
      public List<URIStatus> call() throws AlluxioTException, TException {
        List<URIStatus> result = new ArrayList<URIStatus>();
        for (alluxio.thrift.FileInfo fileInfo : mClient.listStatus(path.getPath())) {
          result.add(new URIStatus(ThriftUtils.fromThrift(fileInfo)));
        }
        return result;
      }
    });
  }
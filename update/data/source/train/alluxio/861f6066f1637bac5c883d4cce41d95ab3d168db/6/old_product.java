public synchronized List<URIStatus> listStatus(final TachyonURI path)
      throws IOException, TachyonException {
    return retryRPC(new RpcCallableThrowsTachyonTException<List<URIStatus>>() {
      @Override
      public List<URIStatus> call() throws TachyonTException, TException {
        List<URIStatus> result = new ArrayList<URIStatus>();
        for (alluxio.thrift.FileInfo fileInfo : mClient.listStatus(path.getPath())) {
          result.add(new URIStatus(ThriftUtils.fromThrift(fileInfo)));
        }
        return result;
      }
    });
  }
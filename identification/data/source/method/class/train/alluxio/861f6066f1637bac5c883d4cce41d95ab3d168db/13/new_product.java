public void reportLostFile(AlluxioURI path)
      throws IOException, FileDoesNotExistException, AlluxioException {
    LineageMasterClient masterClient = mLineageContext.acquireMasterClient();
    try {
      masterClient.reportLostFile(path.getPath());
    } finally {
      mLineageContext.releaseMasterClient(masterClient);
    }
  }
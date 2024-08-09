public void reportLostFile(TachyonURI path)
      throws IOException, FileDoesNotExistException, TachyonException {
    LineageMasterClient masterClient = mLineageContext.acquireMasterClient();
    try {
      masterClient.reportLostFile(path.getPath());
    } finally {
      mLineageContext.releaseMasterClient(masterClient);
    }
  }
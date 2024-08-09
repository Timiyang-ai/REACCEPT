private long reinitializeFile(AlluxioURI path, CreateFileOptions options)
      throws LineageDoesNotExistException, IOException, AlluxioException {
    LineageMasterClient masterClient = mLineageContext.acquireMasterClient();
    try {
      return masterClient.reinitializeFile(path.getPath(), options.getBlockSizeBytes(),
          options.getTtl(), options.toThrift().getTtlExpiryAction());
    } finally {
      mLineageContext.releaseMasterClient(masterClient);
    }
  }
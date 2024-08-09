public void downloadBlob(BlobId blobId, OutputStream outputStream) throws BlobStorageException, IOException {
    azureMetrics.blobDownloadRequestCount.inc();
    Timer.Context storageTimer = azureMetrics.blobDownloadTime.time();
    try {
      BlockBlobClient blobClient = getAzureBlobReference(blobId, false);
      blobClient.download(outputStream);
      azureMetrics.blobDownloadSuccessCount.inc();
    } catch (UncheckedIOException e) {
      // error processing input stream
      azureMetrics.blobDownloadErrorCount.inc();
      throw e.getCause();
    } finally {
      storageTimer.stop();
    }
  }
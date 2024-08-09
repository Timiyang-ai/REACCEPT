public void downloadBlob(BlobId blobId, OutputStream outputStream) throws BlobStorageException {
    azureMetrics.blobDownloadRequestCount.inc();
    Timer.Context storageTimer = azureMetrics.blobDownloadTime.time();
    try {
      String containerName = getAzureContainerName(blobId.getPartition().toPathString());
      String blobName = getAzureBlobName(blobId);
      downloadFile(containerName, blobName, outputStream, true);
      azureMetrics.blobDownloadSuccessCount.inc();
    } catch (Exception e) {
      azureMetrics.blobDownloadErrorCount.inc();
      throw e;
    } finally {
      storageTimer.stop();
    }
  }
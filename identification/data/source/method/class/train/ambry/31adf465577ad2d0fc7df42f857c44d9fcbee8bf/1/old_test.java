  private void downloadBlob(BlobId blobId) throws IOException {
    dataAccessor.downloadBlob(blobId, new ByteArrayOutputStream(blobSize));
  }
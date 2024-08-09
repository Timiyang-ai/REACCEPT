public synchronized FileInfo getFileInfo(final long fileId) throws IOException {
    return retryRPC(() -> FileInfo
        .fromThrift(mClient.getFileInfo(fileId, new GetFileInfoTOptions()).getFileInfo()));
  }
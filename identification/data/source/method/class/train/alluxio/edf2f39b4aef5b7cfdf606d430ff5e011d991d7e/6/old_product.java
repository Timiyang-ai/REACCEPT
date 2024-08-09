public FileInfo getFileInfo(long fileId) throws IOException {
    try {
      return mFileSystemMasterClient.getFileInfo(fileId);
    } catch (AlluxioException e) {
      throw new IOException(e);
    }
  }
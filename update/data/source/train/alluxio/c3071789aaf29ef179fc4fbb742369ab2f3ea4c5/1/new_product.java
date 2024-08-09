@Override
  public FileInfo getInfo(TachyonFile file, GetInfoOptions options)
      throws IOException, FileDoesNotExistException, TachyonException {
    FileSystemMasterClient masterClient = mContext.acquireMasterClient();
    try {
      return masterClient.getStatus(file.getFileId());
    } finally {
      mContext.releaseMasterClient(masterClient);
    }
  }
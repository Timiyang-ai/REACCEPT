@Override
  public List<FileInfo> listStatus(TachyonFile file, ListStatusOptions options)
      throws IOException, FileDoesNotExistException, TachyonException {
    FileSystemMasterClient masterClient = mContext.acquireMasterClient();
    try {
      return masterClient.listStatus(file.getFileId());
    } finally {
      mContext.releaseMasterClient(masterClient);
    }
  }
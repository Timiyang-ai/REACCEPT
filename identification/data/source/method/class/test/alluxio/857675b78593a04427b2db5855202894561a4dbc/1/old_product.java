public long completeUfsFile(long sessionId, long tempUfsFileId)
      throws FileDoesNotExistException, IOException {
    return mUnderFileSystemManager.completeFile(sessionId, tempUfsFileId);
  }
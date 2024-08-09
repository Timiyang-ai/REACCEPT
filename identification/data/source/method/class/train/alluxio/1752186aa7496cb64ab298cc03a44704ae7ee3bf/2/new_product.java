public long completeUfsFile(long sessionId, long tempUfsFileId, Permission perm)
      throws FileDoesNotExistException, IOException {
    return mUnderFileSystemManager.completeFile(sessionId, tempUfsFileId, perm);
  }
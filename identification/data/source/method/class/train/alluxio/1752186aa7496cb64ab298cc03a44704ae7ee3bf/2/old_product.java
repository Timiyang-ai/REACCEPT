public long completeUfsFile(long sessionId, long tempUfsFileId, PermissionStatus ps)
      throws FileDoesNotExistException, IOException {
    return mUnderFileSystemManager.completeFile(sessionId, tempUfsFileId, ps);
  }
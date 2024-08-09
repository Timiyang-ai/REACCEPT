public long completeUfsFile(long sessionId, long tempUfsFileId, String user, String group)
      throws FileDoesNotExistException, IOException {
    return mUnderFileSystemManager.completeFile(sessionId, tempUfsFileId, user, group);
  }
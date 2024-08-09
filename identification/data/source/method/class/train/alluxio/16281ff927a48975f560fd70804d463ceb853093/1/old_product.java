public void addCheckpoint(long userId, int fileId) throws TException, IOException {
    // TODO This part needs to be changed.
    String srcPath = CommonUtils.concatPath(getUserUfsTmpFolder(userId), fileId);
    String ufsDataFolder =
        mTachyonConf.get(Constants.UNDERFS_DATA_FOLDER, Constants.DEFAULT_DATA_FOLDER);
    String dstPath = CommonUtils.concatPath(ufsDataFolder, fileId);
    try {
      if (!mUfs.rename(srcPath, dstPath)) {
        throw new FailedToCheckpointException("Failed to rename " + srcPath + " to " + dstPath);
      }
    } catch (IOException e) {
      throw new FailedToCheckpointException("Failed to rename " + srcPath + " to " + dstPath);
    }
    long fileSize;
    try {
      fileSize = mUfs.getFileSize(dstPath);
    } catch (IOException e) {
      throw new FailedToCheckpointException("Failed to getFileSize " + dstPath);
    }
    mMasterClient.addCheckpoint(mWorkerId, fileId, fileSize, dstPath);
  }
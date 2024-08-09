public void cacheBlock(long userId, long blockId) throws FileDoesNotExistException,
      SuspectedFileSizeException, BlockInfoException, IOException {
    File srcFile = new File(CommonUtils.concat(getUserLocalTempFolder(userId), blockId));
    File dstFile = new File(CommonUtils.concat(mLocalDataFolder, blockId));
    long fileSizeBytes = srcFile.length();
    if (!srcFile.exists()) {
      throw new FileDoesNotExistException("File " + srcFile + " does not exist.");
    }
    synchronized (mLatestBlockAccessTimeMs) {
      if (!srcFile.renameTo(dstFile)) {
        throw new FileDoesNotExistException("Failed to rename file from " + srcFile.getPath()
            + " to " + dstFile.getPath());
      }
      if (mBlockSizes.containsKey(blockId)) {
        mWorkerSpaceCounter.returnUsedBytes(mBlockSizes.get(blockId));
      }
      addBlockId(blockId, fileSizeBytes);
      mUsers.addOwnBytes(userId, -fileSizeBytes);
      mMasterClient.worker_cacheBlock(mWorkerId, mWorkerSpaceCounter.getUsedBytes(), blockId,
          fileSizeBytes);
    }
    LOG.info(userId + " " + dstFile);
  }
public void cleanupUser(long userId) {
    Set<Long> userTempBlocks = mUserIdToTempBlockIdsMap.get(userId);
    if (userTempBlocks != null) {
      for (long blockId : userTempBlocks) {
        TempBlockMeta tempBlock = mBlockIdToTempBlockMap.remove(blockId);
        if (tempBlock != null) {
          reclaimSpace(tempBlock.getBlockSize(), false);
        } else {
          LOG.error("Cannot find blockId {} when cleanup userId {}", blockId, userId);
        }
      }
      mUserIdToTempBlockIdsMap.remove(userId);
    }
  }
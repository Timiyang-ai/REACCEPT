public List<TempBlockMeta> cleanupUser(long userId) {
    List<TempBlockMeta> blocksToRemove = new ArrayList<TempBlockMeta>();
    Set<Long> userTempBlocks = mUserIdToTempBlockIdsMap.get(userId);
    if (userTempBlocks != null) {
      for (long blockId : userTempBlocks) {
        TempBlockMeta tempBlock = mBlockIdToTempBlockMap.remove(blockId);
        if (tempBlock != null) {
          blocksToRemove.add(tempBlock);
          reclaimSpace(tempBlock.getBlockSize());
        } else {
          LOG.error("Cannot find blockId {} when cleanup userId {}", blockId, userId);
        }
      }
      mUserIdToTempBlockIdsMap.remove(userId);
    }
    return blocksToRemove;
  }
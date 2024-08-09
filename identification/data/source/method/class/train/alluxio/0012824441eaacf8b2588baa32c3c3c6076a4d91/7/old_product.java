public List<Long> getBlockIds() {
    List<Long> blockIds = new ArrayList<Long>();
    for (long blockId : mBlockIdToBlockMap.keySet()) {
      blockIds.add(blockId);
    }
    return blockIds;
  }
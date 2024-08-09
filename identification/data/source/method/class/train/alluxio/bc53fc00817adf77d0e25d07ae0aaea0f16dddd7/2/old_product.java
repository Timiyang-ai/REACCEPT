public int getNumberOfBlocks() {
    Preconditions.checkNotNull(mBlockIdsOnTiers, "mBlockIdsOnTiers");

    int numberOfBlocks = 0;
    for (List<Long> blockIds : mBlockIdsOnTiers.values()) {
      numberOfBlocks += blockIds.size();
    }
    return numberOfBlocks;
  }
public Map<String, List<Long>> getBlockList() {
    Preconditions.checkNotNull(mBlockIdsOnTiers, "mBlockIdsOnTiers");

    return mBlockIdsOnTiers;
  }
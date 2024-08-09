public Map<String, List<Long>> getBlockList() {
    Preconditions.checkNotNull(mBlockIdsOnTiers);

    return mBlockIdsOnTiers;
  }
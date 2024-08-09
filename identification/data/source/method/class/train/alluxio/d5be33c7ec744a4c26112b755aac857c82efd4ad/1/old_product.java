public Set<Long> register(List<Long> totalBytesOnTiers, List<Long> usedBytesOnTiers,
      Set<Long> newBlocks) {
    mTotalBytesOnTiers = totalBytesOnTiers;
    mUsedBytesOnTiers = usedBytesOnTiers;
    mCapacityBytes = 0;
    for (long bytes : mTotalBytesOnTiers) {
      mCapacityBytes += bytes;
    }
    mUsedBytes = 0;
    for (long bytes : mUsedBytesOnTiers) {
      mUsedBytes += bytes;
    }

    Set<Long> removedBlocks;
    if (mIsRegistered) {
      // This is a re-register of an existing worker. Assume the new block ownership data is more
      // up-to-date and update the existing block information.
      LOG.info("re-registering an existing workerId: " + mId);

      // Compute the difference between the existing block data, and the new data.
      removedBlocks = Sets.difference(mBlocks, newBlocks);
    } else {
      removedBlocks = Collections.emptySet();
    }

    // Set the new block information.
    mBlocks = newBlocks;

    mIsRegistered = true;
    return removedBlocks;
  }
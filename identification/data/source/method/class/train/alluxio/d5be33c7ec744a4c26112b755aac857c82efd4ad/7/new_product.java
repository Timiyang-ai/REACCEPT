public Set<Long> register(final List<Long> totalBytesOnTiers, final List<Long> usedBytesOnTiers,
      final Set<Long> newBlocks) {
    // validate the number of tiers
    if (totalBytesOnTiers.size() != usedBytesOnTiers.size()) {
      throw new IllegalArgumentException(
          "totalBytesOnTiers should have the same number of tiers as usedBytesOnTiers,"
              + " but totalBytesOnTiers has " + totalBytesOnTiers.size()
              + " tiers, while usedBytesOnTiers has " + usedBytesOnTiers.size() + " tiers");
    }

    // defensive copy
    mTotalBytesOnTiers = new ArrayList<Long>(totalBytesOnTiers);
    mUsedBytesOnTiers = new ArrayList<Long>(usedBytesOnTiers);
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
    mBlocks = new HashSet<Long>(newBlocks);

    mIsRegistered = true;
    return removedBlocks;
  }
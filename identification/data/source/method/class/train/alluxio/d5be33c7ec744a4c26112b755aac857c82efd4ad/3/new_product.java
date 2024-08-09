public Set<Long> register(final StorageTierAssoc globalStorageTierAssoc,
      final List<String> storageTierAliases, final Map<String, Long> totalBytesOnTiers,
      final Map<String, Long> usedBytesOnTiers, final Set<Long> blocks) {
    // If the storage aliases do not have strictly increasing ordinal value based on the total
    // ordering, throw an error
    for (int i = 0; i < storageTierAliases.size() - 1; i++) {
      if (globalStorageTierAssoc.getOrdinal(storageTierAliases.get(i)) >= globalStorageTierAssoc
          .getOrdinal(storageTierAliases.get(i + 1))) {
        throw new IllegalArgumentException(
            "Worker cannot place storage tier " + storageTierAliases.get(i) + " above "
                + storageTierAliases.get(i + 1) + " in the hierarchy");
      }
    }
    mStorageTierAssoc = new WorkerStorageTierAssoc(storageTierAliases);
    // validate the number of tiers
    if (mStorageTierAssoc.size() != totalBytesOnTiers.size()
        || mStorageTierAssoc.size() != usedBytesOnTiers.size()) {
      throw new IllegalArgumentException(
          "totalBytesOnTiers and usedBytesOnTiers should have the same number of tiers as "
              + "storageTierAliases, but storageTierAliases has " + mStorageTierAssoc.size()
              + " tiers, while totalBytesOnTiers has " + totalBytesOnTiers.size()
              + " tiers and usedBytesOnTiers has " + usedBytesOnTiers.size() + " tiers");
    }

    // defensive copy
    mTotalBytesOnTiers = new HashMap<String, Long>(totalBytesOnTiers);
    mUsedBytesOnTiers = new HashMap<String, Long>(usedBytesOnTiers);
    mCapacityBytes = 0;
    for (long bytes : mTotalBytesOnTiers.values()) {
      mCapacityBytes += bytes;
    }
    mUsedBytes = 0;
    for (long bytes : mUsedBytesOnTiers.values()) {
      mUsedBytes += bytes;
    }

    Set<Long> removedBlocks;
    if (mIsRegistered) {
      // This is a re-register of an existing worker. Assume the new block ownership data is more
      // up-to-date and update the existing block information.
      LOG.info("re-registering an existing workerId: {}", mId);

      // Compute the difference between the existing block data, and the new data.
      removedBlocks = Sets.difference(mBlocks, blocks);
    } else {
      removedBlocks = Collections.emptySet();
    }

    // Set the new block information.
    mBlocks = new HashSet<Long>(blocks);

    mIsRegistered = true;
    return removedBlocks;
  }
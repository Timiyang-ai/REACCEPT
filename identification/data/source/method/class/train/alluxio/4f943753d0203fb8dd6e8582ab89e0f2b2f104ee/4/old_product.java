public BlockInfo getBlockInfo(long blockId) throws BlockInfoException {
    MasterBlockInfo masterBlockInfo = mBlocks.get(blockId);
    if (masterBlockInfo != null) {
      // Construct the block info object to return.

      // "Join" to get all the addresses of the workers.
      List<BlockLocation> locations = new ArrayList<BlockLocation>();
      for (MasterBlockLocation masterBlockLocation : masterBlockInfo.getBlockLocations()) {
        MasterWorkerInfo workerInfo =
            mWorkers.getFirstByField(mIdIndex, masterBlockLocation.getWorkerId());
        if (workerInfo != null) {
          locations.add(new BlockLocation(masterBlockLocation.getWorkerId(),
              workerInfo.getAddress(), masterBlockLocation.getTier()));
        }
      }
      return new BlockInfo(masterBlockInfo.getBlockId(), masterBlockInfo.getLength(), locations);
    }
    throw new BlockInfoException("Block info not found for " + blockId);
  }
public void removeBlocks(List<Long> blockIds, boolean delete) {
    synchronized (mBlocks) {
      synchronized (mWorkers) {
        for (long blockId : blockIds) {
          MasterBlockInfo masterBlockInfo = mBlocks.get(blockId);
          if (masterBlockInfo == null) {
            continue;
          }
          for (long workerId : new ArrayList<Long>(masterBlockInfo.getWorkers())) {
            MasterWorkerInfo workerInfo = mWorkers.getFirstByField(mIdIndex, workerId);
            if (workerInfo != null) {
              workerInfo.updateToRemovedBlock(true, blockId);
            }
          }
          // Two cases here:
          // 1) For delete: delete the block metadata.
          // 2) For free: keep the block metadata. mLostBlocks will be changed in
          // processWorkerRemovedBlocks
          if (delete) {
            // Make sure blockId is removed from mLostBlocks when the block metadata is deleted.
            // Otherwise blockId in mLostBlock can be dangling index if the metadata is gone.
            mLostBlocks.remove(blockId);
            mBlocks.remove(blockId);
          }
        }
      }
    }
  }
public void removeBlocks(List<Long> blockIds, boolean delete) {
    synchronized (mBlocks) {
      synchronized (mWorkers) {
        for (long blockId : blockIds) {
          MasterBlockInfo masterBlockInfo = mBlocks.get(blockId);
          if (masterBlockInfo == null) {
            continue;
          }
          for (long workerId : new ArrayList<Long>(masterBlockInfo.getWorkers())) {
            masterBlockInfo.removeWorker(workerId);
            MasterWorkerInfo worker = mWorkers.getFirstByField(mIdIndex, workerId);
            if (worker != null) {
              worker.updateToRemovedBlock(true, blockId);
            }
          }
          if (delete) {
            mBlocks.remove(blockId);
          } else {
            mLostBlocks.add(blockId);
          }
        }
      }
    }
  }
public BlockInfo getBlockInfo(long blockId) throws BlockInfoException {
    synchronized (mBlocks) {
      MasterBlockInfo masterBlockInfo = mBlocks.get(blockId);
      if (masterBlockInfo == null) {
        throw new BlockInfoException(ExceptionMessage.BLOCK_META_NOT_FOUND, blockId);
      }
      // Construct the block info object to return.
      synchronized (mWorkers) {
        return generateBlockInfo(masterBlockInfo);
      }
    }
  }
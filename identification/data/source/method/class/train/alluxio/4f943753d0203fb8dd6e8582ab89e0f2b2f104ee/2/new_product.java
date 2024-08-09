public BlockInfo getBlockInfo(long blockId) throws BlockInfoException {
    MasterBlockInfo block = mBlocks.get(blockId);
    if (block == null) {
      throw new BlockInfoException(ExceptionMessage.BLOCK_META_NOT_FOUND, blockId);
    }
    synchronized (block) {
      return generateBlockInfo(block);
    }
  }
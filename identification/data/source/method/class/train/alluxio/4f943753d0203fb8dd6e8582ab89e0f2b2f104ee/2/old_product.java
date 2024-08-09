public BlockInfo getBlockInfo(long blockId) throws BlockInfoException {
    MasterBlockInfo masterBlockInfo = mBlocks.get(blockId);
    if (masterBlockInfo == null) {
      throw new BlockInfoException(ExceptionMessage.BLOCK_META_NOT_FOUND, blockId);
    }
    synchronized (masterBlockInfo) {
      return generateBlockInfo(masterBlockInfo);
    }
  }
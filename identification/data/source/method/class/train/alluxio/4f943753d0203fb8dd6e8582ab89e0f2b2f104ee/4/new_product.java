public BlockInfo getBlockInfo(long blockId) throws BlockInfoException {
    MasterBlockInfo masterBlockInfo = mBlocks.get(blockId);
    if (masterBlockInfo == null) {
      throw new BlockInfoException("Block info not found for " + blockId);
    }
    // Construct the block info object to return.
    return generateBlockInfo(masterBlockInfo);
  }
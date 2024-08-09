public List<BlockMeta> getEvictableBlocks() {
    List<BlockMeta> filteredList = new ArrayList<BlockMeta>();

    for (BlockMeta blockMeta : mDir.getBlocks()) {
      long blockId = blockMeta.getBlockId();
      if (!mManagerView.isBlockPinned(blockId) && !mManagerView.isBlockLocked(blockId)) {
        filteredList.add(blockMeta);
      }
    }
    return filteredList;
  }
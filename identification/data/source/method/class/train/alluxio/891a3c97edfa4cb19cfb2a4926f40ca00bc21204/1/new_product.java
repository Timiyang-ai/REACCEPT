public List<BlockMeta> getEvictableBlocks() {
    List<BlockMeta> filteredList = new ArrayList<>();

    for (BlockMeta blockMeta : mDir.getBlocks()) {
      long blockId = blockMeta.getBlockId();
      if (mManagerView.isBlockEvictable(blockId)) {
        filteredList.add(blockMeta);
      }
    }
    return filteredList;
  }
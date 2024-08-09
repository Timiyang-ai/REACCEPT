public List<BlockMeta> getEvictableBlocks() {
    List<BlockMeta> filteredList = new ArrayList<>();

    for (BlockMeta blockMeta : mDir.getBlocks()) {
      long blockID = blockMeta.getBlockID();
      if (mManagerView.isBlockEvictable(blockID)) {
        filteredList.add(blockMeta);
      }
    }
    return filteredList;
  }
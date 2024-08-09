public List<FileBlockInfo> getFileBlockInfoList(long fileId) throws FileDoesNotExistException {
    synchronized (mInodeTree) {
      Inode inode = mInodeTree.getInodeById(fileId);
      if (inode.isDirectory()) {
        throw new FileDoesNotExistException("FileId " + fileId + " is not a file.");
      }
      InodeFile tFile = (InodeFile) inode;
      List<BlockInfo> blockInfoList = mBlockMaster.getBlockInfoList(tFile.getBlockIds());

      List<FileBlockInfo> ret = new ArrayList<FileBlockInfo>();
      for (BlockInfo blockInfo : blockInfoList) {
        ret.add(generateFileBlockInfo(tFile, blockInfo));
      }
      return ret;
    }
  }
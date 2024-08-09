public List<FileBlockInfo> getFileBlockInfoList(AlluxioURI path)
      throws FileDoesNotExistException, InvalidPathException {
    MasterContext.getMasterSource().incGetFileBlockInfoOps(1);
    synchronized (mInodeTree) {
      Inode<?> inode = mInodeTree.getInodeByPath(path);
      if (inode.isDirectory()) {
        throw new FileDoesNotExistException(ExceptionMessage.PATH_MUST_BE_FILE.getMessage(path));
      }
      InodeFile file = (InodeFile) inode;
      List<BlockInfo> blockInfoList = mBlockMaster.getBlockInfoList(file.getBlockIds());

      List<FileBlockInfo> ret = new ArrayList<FileBlockInfo>();
      for (BlockInfo blockInfo : blockInfoList) {
        ret.add(generateFileBlockInfo(file, blockInfo));
      }
      MasterContext.getMasterSource().incFileBlockInfosGot(ret.size());
      return ret;
    }
  }
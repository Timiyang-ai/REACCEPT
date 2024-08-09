public List<FileBlockInfo> getFileBlockInfoList(TachyonURI path)
      throws FileDoesNotExistException, InvalidPathException {
    long fileId = getFileId(path);
    synchronized (mInodeTree) {
      return getFileBlockInfoList(fileId);
    }
  }
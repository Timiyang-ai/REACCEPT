public AlluxioURI getPath(InodeView inode) throws FileDoesNotExistException {
    StringBuilder builder = new StringBuilder();
    computePathForInode(inode, builder);
    return new AlluxioURI(builder.toString());
  }
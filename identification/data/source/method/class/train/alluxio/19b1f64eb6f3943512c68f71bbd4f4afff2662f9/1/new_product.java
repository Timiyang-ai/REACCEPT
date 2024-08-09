public AlluxioURI getPath(Inode<?> inode) throws FileDoesNotExistException {
    Preconditions.checkState(inode.isWriteLocked() || inode.isReadLocked());
    StringBuilder builder = new StringBuilder();
    computePathForInode(inode, builder);
    return new AlluxioURI(builder.toString());
  }
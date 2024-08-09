public AlluxioURI getPath(Inode inode) {
    if (isRootId(inode.getId())) {
      return new AlluxioURI(AlluxioURI.SEPARATOR);
    }
    if (isRootId(inode.getParentId())) {
      return new AlluxioURI(AlluxioURI.SEPARATOR + inode.getName());
    }
    return getPath(mInodes.getFirstByField(mIdIndex, inode.getParentId())).join(inode.getName());
  }
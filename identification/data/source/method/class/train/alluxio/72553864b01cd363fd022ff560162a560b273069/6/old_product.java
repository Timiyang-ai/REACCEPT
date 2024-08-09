public synchronized Inode getChild(long id) {
    return mChildren.getFirstByField(mIdIndex, id);
  }
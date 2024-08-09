public void deleteInode(Inode inode) throws FileDoesNotExistException {
    deleteInode(inode, System.currentTimeMillis());
  }
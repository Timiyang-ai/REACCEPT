public void remove(Inode<?> inode) {
    TtlBucket bucket = getBucketContaining(inode);
    if (bucket != null) {
      bucket.removeInode(inode);
    }
  }
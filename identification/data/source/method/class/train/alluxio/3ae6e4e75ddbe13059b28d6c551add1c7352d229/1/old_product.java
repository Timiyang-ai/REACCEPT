public synchronized boolean delete(TachyonURI path) {
    LOG.info("Unmounting " + path.getPath());
    if (mMountTable.containsKey(path.getPath())) {
      mMountTable.remove(path.getPath());
      return true;
    }
    // Cannot unmount a path that does not correspond to a mount point.
    return false;
  }
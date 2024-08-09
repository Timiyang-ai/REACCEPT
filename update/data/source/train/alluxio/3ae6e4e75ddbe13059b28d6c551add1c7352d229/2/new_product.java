public synchronized boolean delete(TachyonURI uri) throws InvalidPathException {
    String path = uri.getPath();
    LOG.info("Unmounting " + path);
    if (mMountTable.containsKey(path)) {
      for (Map.Entry<String, TachyonURI> entry : mMountTable.entrySet()) {
        String tachyonPath = entry.getKey();
        if (!path.equals(tachyonPath) && PathUtils.hasPrefix(tachyonPath, path)) {
          // Cannout unmount a path if it has nested mount points.
          return false;
        }
      }
      mMountTable.remove(path);
      return true;
    }
    // Cannot unmount a path that does not correspond to a mount point.
    return false;
  }
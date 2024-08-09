public long getFileId(TachyonURI path) throws InvalidPathException {
    synchronized (mInodeTree) {
      if (mInodeTree.exists(path)) {
        return mInodeTree.getInodeByPath(path).getId();
      } else {
        try {
          return loadMetadata(path, true);
        } catch (Exception e) {
          throw new InvalidPathException("Could not find path: " + path);
        }
      }
    }
  }
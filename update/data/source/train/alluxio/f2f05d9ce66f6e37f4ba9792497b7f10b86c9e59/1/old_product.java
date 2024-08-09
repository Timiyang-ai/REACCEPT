public static synchronized String getUnderFSClass() {
    if (sUnderFSClass == null) {
      sUnderFSClass = LocalFileSystemCluster.class.getName();
    }
    return sUnderFSClass;
  }
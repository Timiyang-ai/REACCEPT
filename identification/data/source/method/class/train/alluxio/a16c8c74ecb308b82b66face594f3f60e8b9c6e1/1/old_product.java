public static boolean readEOFReturnsNegative() {
    // TODO(hy): Should be dynamically determined - may need additional method on UnderFileSystem.
    return sUnderFSClass != null
        && sUnderFSClass.equals("alluxio.underfs.hdfs.LocalMiniDFSCluster");
  }
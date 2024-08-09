public static int getNumberOfClusters(Path clusterOutputPath, Configuration conf) throws IOException {
    FileSystem fileSystem = clusterOutputPath.getFileSystem(conf);
    FileStatus[] clusterFiles = fileSystem.listStatus(clusterOutputPath, CLUSTER_FINAL);
    int numberOfClusters = 0;
    Iterator<?> it = new SequenceFileDirValueIterator<Writable>(clusterFiles[0].getPath(),
                                                                PathType.LIST,
                                                                PathFilters.partFilter(),
                                                                null,
                                                                true,
                                                                conf);
    while (it.hasNext()) {
      it.next();
      numberOfClusters++;
    }
    return numberOfClusters;
  }
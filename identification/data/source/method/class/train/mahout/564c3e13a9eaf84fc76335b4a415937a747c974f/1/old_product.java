public static int getNumberOfClusters(Path clusterOutputPath, Configuration conf) throws IOException,
                                                                                   InstantiationException,
                                                                                   IllegalAccessException {
    int numberOfClusters = 0;
    FileStatus[] partFiles = getPartFiles(clusterOutputPath, conf);
    for (FileStatus fileStatus : partFiles) {
      SequenceFile.Reader reader = new SequenceFile.Reader(FileSystem.get(conf), fileStatus.getPath(), conf);
      WritableComparable key = (WritableComparable) reader.getKeyClass().newInstance();
      Writable value = (Writable) reader.getValueClass().newInstance();
      while (reader.next(key, value)) {
        numberOfClusters++;
      }
      reader.close();
    }
    return numberOfClusters;
  }
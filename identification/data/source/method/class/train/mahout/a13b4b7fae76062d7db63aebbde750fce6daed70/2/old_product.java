protected static void processOutput(JobContext job,
                                      Path outputPath,
                                      int[] firstIds,
                                      TreeID[] keys,
                                      Node[] trees,
                                      PredictionCallback callback) throws IOException {
    Preconditions.checkArgument((keys == null && trees == null) || (keys != null && trees != null),
        "if keys is null, trees should also be null");
    Preconditions.checkArgument(keys == null || keys.length == trees.length, "keys.length != trees.length");

    Configuration conf = job.getConfiguration();

    FileSystem fs = outputPath.getFileSystem(conf);

    Path[] outfiles = DFUtils.listOutputFiles(fs, outputPath);

    // read all the outputs
    TreeID key = new TreeID();
    MapredOutput value = new MapredOutput();

    int index = 0;
    for (Path path : outfiles) {
      Reader reader = new Reader(fs, path, conf);

      try {
        while (reader.next(key, value)) {
          if (keys != null) {
            keys[index] = key.clone();
          }

          if (trees != null) {
            trees[index] = value.getTree();
          }

          processOutput(firstIds, key, value, callback);

          index++;
        }
      } finally {
        reader.close();
      }
    }

    // make sure we got all the keys/values
    if ((keys != null) && (index != keys.length)) {
      throw new IllegalStateException("Some key/values are missing from the output");
    }
  }
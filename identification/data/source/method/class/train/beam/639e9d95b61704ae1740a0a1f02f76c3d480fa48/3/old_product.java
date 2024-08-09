protected final List<String> generateDestinationFilenames(int numFiles) {
      List<String> destFilenames = new ArrayList<>();
      String extension = getSink().extension;
      String baseOutputFilename = getSink().baseOutputFilename;
      String fileNamingTemplate = getSink().fileNamingTemplate;

      String suffix = getFileExtension(extension);
      for (int i = 0; i < numFiles; i++) {
        destFilenames.add(IOChannelUtils.constructName(
            baseOutputFilename, fileNamingTemplate, suffix, i, numFiles));
      }

      int numDistinctShards = new HashSet<String>(destFilenames).size();
      Preconditions.checkState(numDistinctShards == numFiles,
          "Shard name template '%s' only generated %s distinct file names for %s files.",
          fileNamingTemplate, numDistinctShards, numFiles);

      return destFilenames;
    }
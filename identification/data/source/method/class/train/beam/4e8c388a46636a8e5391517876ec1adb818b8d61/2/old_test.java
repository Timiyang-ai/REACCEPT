  private static String constructName(
      String baseFilename,
      String shardTemplate,
      String suffix,
      int shardNum,
      int numShards,
      String paneStr,
      String windowStr) {
    ResourceId constructed =
        DefaultFilenamePolicy.constructName(
            FileSystems.matchNewResource(baseFilename, false),
            shardTemplate,
            suffix,
            shardNum,
            numShards,
            paneStr,
            windowStr);
    return constructed.toString();
  }
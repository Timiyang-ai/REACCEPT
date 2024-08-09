static String constructName(String prefix, String shardTemplate, String suffix, int shardNum,
      int numShards) {
    return constructName(prefix, shardTemplate, suffix, shardNum, numShards, null, null);
  }
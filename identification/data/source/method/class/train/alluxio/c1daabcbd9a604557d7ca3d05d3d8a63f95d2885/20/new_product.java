public static String getTmpDir(List<String> tmpDirs) {
    Preconditions.checkState(!tmpDirs.isEmpty(), "No temporary directories available");
    if (tmpDirs.size() == 1) {
      return tmpDirs.get(0);
    }
    // Use existing random instead of ThreadLocal because contention is not expected to be high.
    return tmpDirs.get(RANDOM.nextInt(tmpDirs.size()));
  }
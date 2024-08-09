public static String getTmpDir() {
    Preconditions.checkState(!TMP_DIRS.isEmpty(), "No temporary directories configured");
    if (TMP_DIRS.size() == 1) {
      return TMP_DIRS.get(0);
    }
    // Use existing random instead of ThreadLocal because contention is not expected to be high.
    return TMP_DIRS.get(RANDOM.nextInt(TMP_DIRS.size()));
  }
@VisibleForTesting
  static String createSnapshotFileName(String serverName, long index) {
    return String.format("%s-%d.%s",
        serverName,
        index,
        EXTENSION);
  }
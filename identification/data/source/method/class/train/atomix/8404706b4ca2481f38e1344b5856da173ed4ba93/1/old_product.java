@VisibleForTesting
  static String createSnapshotFileName(String serviceName, long serviceId, long index) {
    return String.format("%s-%d-%d.%s",
        serviceName,
        serviceId,
        index,
        EXTENSION);
  }
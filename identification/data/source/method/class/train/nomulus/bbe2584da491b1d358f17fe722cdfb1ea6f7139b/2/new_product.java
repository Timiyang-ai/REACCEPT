@VisibleForTesting
  ImmutableMap<Integer, DateTime> readBucketTimestamps() {
    // Use a fresh session cache so that we get the latest data from Datastore.
    return ofy.doWithFreshSessionCache(
        () -> {
          ImmutableMap.Builder<Integer, DateTime> results = new ImmutableMap.Builder<>();
          for (CommitLogBucket bucket : CommitLogBucket.loadAllBuckets()) {
            results.put(bucket.getBucketNum(), bucket.getLastWrittenTime());
          }
          return results.build();
        });
  }
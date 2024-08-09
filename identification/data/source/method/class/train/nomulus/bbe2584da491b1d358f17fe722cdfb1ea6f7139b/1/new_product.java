@VisibleForTesting
  ImmutableMap<Integer, DateTime> readBucketTimestamps() {
    // Use a fresh session cache so that we get the latest data from Datastore.
    return ofy.doWithFreshSessionCache(
        () ->
            CommitLogBucket.loadAllBuckets()
                .stream()
                .collect(
                    ImmutableMap.toImmutableMap(
                        CommitLogBucket::getBucketNum, CommitLogBucket::getLastWrittenTime)));
  }
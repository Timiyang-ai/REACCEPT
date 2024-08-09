public static CommitLogBucket loadBucket(Key<CommitLogBucket> bucketKey) {
    CommitLogBucket bucket = ofy().load().key(bucketKey).now();
    return (bucket == null)
        ? new CommitLogBucket.Builder().setBucketNum(bucketKey.getId()).build()
        : bucket;
  }
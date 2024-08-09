@VisibleForTesting
  static BucketOptions createBucketOptions(BucketBoundaries bucketBoundaries) {
    return BucketOptions.newBuilder()
        .setExplicitBuckets(Explicit.newBuilder().addAllBounds(bucketBoundaries.getBoundaries()))
        .build();
  }
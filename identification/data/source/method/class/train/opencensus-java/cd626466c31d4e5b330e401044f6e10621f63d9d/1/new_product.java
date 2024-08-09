@VisibleForTesting
  static BucketOptions createBucketOptions(
      @javax.annotation.Nullable
          io.opencensus.metrics.export.Distribution.BucketOptions bucketOptions) {
    final BucketOptions.Builder builder = BucketOptions.newBuilder();
    if (bucketOptions == null) {
      return builder.build();
    }

    return bucketOptions.match(
        bucketOptionsExplicitFunction, Functions.<BucketOptions>throwIllegalArgumentException());
  }
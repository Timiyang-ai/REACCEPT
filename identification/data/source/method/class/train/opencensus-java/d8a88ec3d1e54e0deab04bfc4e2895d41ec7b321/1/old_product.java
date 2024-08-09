@VisibleForTesting
  static Distribution createDistribution(io.opencensus.metrics.export.Distribution distribution) {
    return Distribution.newBuilder()
        .setBucketOptions(createBucketOptions(distribution.getBucketOptions()))
        .addAllBucketCounts(createBucketCounts(distribution.getBuckets()))
        .setCount(distribution.getCount())
        .setMean(distribution.getCount() == 0 ? 0 : distribution.getSum() / distribution.getCount())
        .setSumOfSquaredDeviation(distribution.getSumOfSquaredDeviations())
        .build();
  }
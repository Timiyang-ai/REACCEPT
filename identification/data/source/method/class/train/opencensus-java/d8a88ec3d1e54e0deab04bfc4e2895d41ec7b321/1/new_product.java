@VisibleForTesting
  static Distribution createDistribution(io.opencensus.metrics.export.Distribution distribution) {
    Distribution.Builder builder =
        Distribution.newBuilder()
            .setBucketOptions(createBucketOptions(distribution.getBucketOptions()))
            .setCount(distribution.getCount())
            .setMean(
                distribution.getCount() == 0 ? 0 : distribution.getSum() / distribution.getCount())
            .setSumOfSquaredDeviation(distribution.getSumOfSquaredDeviations());
    setBucketCountsAndExemplars(distribution.getBuckets(), builder);
    return builder.build();
  }
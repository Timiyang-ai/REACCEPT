@VisibleForTesting
  static Distribution createDistribution(
      DistributionData distributionData, BucketBoundaries bucketBoundaries) {
    return Distribution.newBuilder()
        .setBucketOptions(createBucketOptions(bucketBoundaries))
        .addAllBucketCounts(distributionData.getBucketCounts())
        .setCount(distributionData.getCount())
        .setMean(distributionData.getMean())
        // TODO(songya): uncomment this once Stackdriver supports setting max and min.
        // .setRange(
        //    Range.newBuilder()
        //        .setMax(distributionData.getMax())
        //        .setMin(distributionData.getMin())
        //        .build())
        .setSumOfSquaredDeviation(distributionData.getSumOfSquaredDeviations())
        .build();
  }
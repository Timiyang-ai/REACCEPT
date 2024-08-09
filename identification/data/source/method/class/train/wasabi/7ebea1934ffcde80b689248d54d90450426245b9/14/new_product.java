Map<Bucket.Label, BucketStatistics> calculateBucketStatistics(Map<Bucket.Label, BucketCounts> buckets,
                                                                  BinomialMetric metric, double effectSize,
                                                                  Parameters.Mode mode) {
        Map<Bucket.Label, BucketStatistics> bucketsWithStats = new HashMap<>();

        for (BucketCounts bucket : buckets.values()) {
            Bucket.Label bucketLabel = bucket.getLabel();
            BucketStatistics bucketWithStats = new BucketStatistics.Builder()
                    .withBucketCounts(bucket)
                    .withBucketComparisons(new HashMap<>())
                    .build();

            analysisTools.generateRate(bucketWithStats, metric);
            bucketsWithStats.put(bucketLabel, bucketWithStats);
        }

        analysisTools.generateBucketComparison(bucketsWithStats, metric, effectSize, mode);

        return bucketsWithStats;
    }
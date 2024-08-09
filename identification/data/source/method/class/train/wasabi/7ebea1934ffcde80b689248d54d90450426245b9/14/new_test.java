    @Test
    public void calculateBucketStatisticsTest() {
        Map<Bucket.Label, BucketCounts> buckets = new HashMap<Bucket.Label, BucketCounts>();
        buckets.put(Bucket.Label.valueOf("Test"), new BucketCounts.Builder().build());
        BinomialMetrics.BinomialMetric binomialMetric = mock(BinomialMetrics.BinomialMetric.class);
        Map<Bucket.Label, BucketStatistics> result = this.analyticsImpl.calculateBucketStatistics(buckets,
                binomialMetric, 1.0, Parameters.Mode.TEST);
        assertThat(result.size(), is(1));

    }
  @Test
  public void createMutableAggregation() {
    BucketBoundaries bucketBoundaries = BucketBoundaries.create(Arrays.asList(-1.0, 0.0, 1.0));

    assertThat(
            RecordUtils.createMutableAggregation(Sum.create(), MEASURE_DOUBLE).toAggregationData())
        .isEqualTo(SumDataDouble.create(0));
    assertThat(RecordUtils.createMutableAggregation(Sum.create(), MEASURE_LONG).toAggregationData())
        .isEqualTo(SumDataLong.create(0));
    assertThat(
            RecordUtils.createMutableAggregation(Count.create(), MEASURE_DOUBLE)
                .toAggregationData())
        .isEqualTo(CountData.create(0));
    assertThat(
            RecordUtils.createMutableAggregation(Count.create(), MEASURE_LONG).toAggregationData())
        .isEqualTo(CountData.create(0));
    assertThat(
            RecordUtils.createMutableAggregation(Mean.create(), MEASURE_DOUBLE).toAggregationData())
        .isEqualTo(MeanData.create(0, 0));
    assertThat(
            RecordUtils.createMutableAggregation(Mean.create(), MEASURE_LONG).toAggregationData())
        .isEqualTo(MeanData.create(0, 0));
    assertThat(
            RecordUtils.createMutableAggregation(LastValue.create(), MEASURE_DOUBLE)
                .toAggregationData())
        .isEqualTo(LastValueDataDouble.create(Double.NaN));
    assertThat(
            RecordUtils.createMutableAggregation(LastValue.create(), MEASURE_LONG)
                .toAggregationData())
        .isEqualTo(LastValueDataLong.create(0));

    MutableDistribution mutableDistribution =
        (MutableDistribution)
            RecordUtils.createMutableAggregation(
                Distribution.create(bucketBoundaries), MEASURE_DOUBLE);
    assertThat(mutableDistribution.getSumOfSquaredDeviations()).isWithin(EPSILON).of(0);
    assertThat(mutableDistribution.getBucketCounts()).isEqualTo(new long[2]);
  }
@VisibleForTesting
  static TypedValue createTypedValue(
      final Aggregation aggregation, AggregationData aggregationData) {
    final TypedValue.Builder builder = TypedValue.newBuilder();
    aggregationData.match(
        new Function<SumDataDouble, Void>() {
          @Override
          public Void apply(SumDataDouble arg) {
            builder.setDoubleValue(arg.getSum());
            return null;
          }
        },
        new Function<SumDataLong, Void>() {
          @Override
          public Void apply(SumDataLong arg) {
            builder.setInt64Value(arg.getSum());
            return null;
          }
        },
        new Function<CountData, Void>() {
          @Override
          public Void apply(CountData arg) {
            builder.setInt64Value(arg.getCount());
            return null;
          }
        },
        new Function<AggregationData.MeanData, Void>() {
          @Override
          public Void apply(AggregationData.MeanData arg) {
            builder.setDoubleValue(arg.getMean());
            return null;
          }
        },
        new Function<DistributionData, Void>() {
          @Override
          public Void apply(DistributionData arg) {
            checkArgument(
                aggregation instanceof Aggregation.Distribution,
                "Aggregation and AggregationData mismatch.");
            builder.setDistributionValue(
                createDistribution(
                    arg, ((Aggregation.Distribution) aggregation).getBucketBoundaries()));
            return null;
          }
        },
        Functions.</*@Nullable*/ Void>throwIllegalArgumentException());
    return builder.build();
  }
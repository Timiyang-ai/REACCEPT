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
        new Function<LastValueDataDouble, Void>() {
          @Override
          public Void apply(LastValueDataDouble arg) {
            builder.setDoubleValue(arg.getLastValue());
            return null;
          }
        },
        new Function<LastValueDataLong, Void>() {
          @Override
          public Void apply(LastValueDataLong arg) {
            builder.setInt64Value(arg.getLastValue());
            return null;
          }
        },
        new Function<AggregationData, Void>() {
          @Override
          public Void apply(AggregationData arg) {
            // TODO(songya): remove this once Mean aggregation is completely removed. Before that
            // we need to continue supporting Mean, since it could still be used by users and some
            // deprecated RPC views.
            if (arg instanceof AggregationData.MeanData) {
              builder.setDoubleValue(((AggregationData.MeanData) arg).getMean());
              return null;
            }
            throw new IllegalArgumentException("Unknown Aggregation");
          }
        });
    return builder.build();
  }
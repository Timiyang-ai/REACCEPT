@VisibleForTesting
  static TypedValue createTypedValue(
      final Aggregation aggregation, AggregationData aggregationData) {
    return aggregationData.match(
        typedValueSumDoubleFunction,
        typedValueSumLongFunction,
        typedValueCountFunction,
        new Function<DistributionData, TypedValue>() {
          @Override
          public TypedValue apply(DistributionData arg) {
            TypedValue.Builder builder = TypedValue.newBuilder();
            checkArgument(
                aggregation instanceof Aggregation.Distribution,
                "Aggregation and AggregationData mismatch.");
            builder.setDistributionValue(
                createDistribution(
                    arg, ((Aggregation.Distribution) aggregation).getBucketBoundaries()));
            return builder.build();
          }
        },
        typedValueLastValueDoubleFunction,
        typedValueLastValueLongFunction,
        typedValueMeanFunction);
  }
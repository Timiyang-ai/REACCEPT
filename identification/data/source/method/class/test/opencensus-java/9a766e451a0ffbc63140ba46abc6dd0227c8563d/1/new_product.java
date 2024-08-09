@VisibleForTesting
  static MutableAggregation createMutableAggregation(
      Aggregation aggregation, final Measure measure) {
    return aggregation.match(
        new Function<Sum, MutableAggregation>() {
          @Override
          public MutableAggregation apply(Sum arg) {
            return measure.match(
                CreateMutableSumDouble.INSTANCE,
                CreateMutableSumLong.INSTANCE,
                Functions.<MutableAggregation>throwAssertionError());
          }
        },
        CreateMutableCount.INSTANCE,
        CreateMutableDistribution.INSTANCE,
        new Function<LastValue, MutableAggregation>() {
          @Override
          public MutableAggregation apply(LastValue arg) {
            return measure.match(
                CreateMutableLastValueDouble.INSTANCE,
                CreateMutableLastValueLong.INSTANCE,
                Functions.<MutableAggregation>throwAssertionError());
          }
        },
        AggregationDefaultFunction.INSTANCE);
  }
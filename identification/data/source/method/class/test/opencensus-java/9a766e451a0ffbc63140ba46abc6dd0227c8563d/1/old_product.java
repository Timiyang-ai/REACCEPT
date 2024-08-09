@VisibleForTesting
  static MutableAggregation createMutableAggregation(Aggregation aggregation) {
    return aggregation.match(
        CreateMutableSum.INSTANCE,
        CreateMutableCount.INSTANCE,
        CreateMutableDistribution.INSTANCE,
        CreateMutableLastValue.INSTANCE,
        AggregationDefaultFunction.INSTANCE);
  }
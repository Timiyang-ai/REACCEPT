@VisibleForTesting
  ImmutableMap<Integer, DateTime> computeBucketCheckpointTimes(
      ImmutableMap<Integer, DateTime> firstPassTimes,
      final DateTime threshold) {
    return ImmutableMap.copyOf(transformValues(firstPassTimes, new Function<DateTime, DateTime>() {
        @Override
        public DateTime apply(DateTime firstPassTime) {
          return earliestOf(firstPassTime, threshold);
        }}));
  }
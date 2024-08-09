@VisibleForTesting
  ImmutableMap<Integer, DateTime> computeBucketCheckpointTimes(
      ImmutableMap<Integer, DateTime> firstPassTimes,
      final DateTime threshold) {
    return ImmutableMap.copyOf(
        transformValues(firstPassTimes, firstPassTime -> earliestOf(firstPassTime, threshold)));
  }
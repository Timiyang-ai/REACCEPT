  @Test
  public void test_computeBucketCheckpointTimes_earlyThreshold_setsEverythingToThreshold() {
    DateTime now = clock.nowUtc();
    ImmutableMap<Integer, DateTime> bucketTimes =
        ImmutableMap.of(1, now.minusMillis(1), 2, now, 3, now.plusMillis(1));
    assertThat(strategy.computeBucketCheckpointTimes(bucketTimes, now.minusMillis(2)).values())
        .containsExactly(now.minusMillis(2), now.minusMillis(2), now.minusMillis(2));
  }
  @Test(dataProvider = "caches")
  @CacheSpec(compute = Compute.SYNC, implementation = Implementation.Caffeine,
      population = Population.FULL, maximumSize = Maximum.FULL)
  public void afterWrite_drainFullWriteBuffer(Cache<Integer, Integer> cache, CacheContext context) {
    BoundedLocalCache<Integer, Integer> localCache = asBoundedLocalCache(cache);
    localCache.drainStatus = PROCESSING_TO_IDLE;

    int[] processed = { 0 };
    Runnable pendingTask = () -> processed[0]++;

    int[] expectedCount = { 0 };
    while (localCache.writeBuffer().offer(pendingTask)) {
      expectedCount[0]++;
    }

    int[] triggered = { 0 };
    Runnable triggerTask = () -> triggered[0] = 1 + expectedCount[0];
    localCache.afterWrite(triggerTask);

    assertThat(processed[0], is(expectedCount[0]));
    assertThat(triggered[0], is(expectedCount[0] + 1));
  }
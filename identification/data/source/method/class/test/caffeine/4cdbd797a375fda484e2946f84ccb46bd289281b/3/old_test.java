  @CacheSpec
  @CheckNoWriter @CheckNoStats
  @Test(dataProvider = "caches")
  public void stats(Cache<Integer, Integer> cache, CacheContext context) {
    CacheStats stats = cache.stats()
        .plus(new CacheStats(1, 2, 3, 4, 5, 6, 7)
        .minus(new CacheStats(6, 5, 4, 3, 2, 1, 0)));
    assertThat(stats, is(new CacheStats(0, 0, 0, 1, 3, 5, 7)));
    assertThat(cache.policy().isRecordingStats(), is(context.isRecordingStats()));
  }
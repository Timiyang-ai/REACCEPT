  @CheckNoWriter @CheckNoStats
  @Test(dataProvider = "caches")
  @CacheSpec(removalListener = { Listener.DEFAULT, Listener.REJECTING })
  public void invalidate_absent(Cache<Integer, Integer> cache, CacheContext context) {
    cache.invalidate(context.absentKey());
    assertThat(cache.estimatedSize(), is(context.initialSize()));
  }
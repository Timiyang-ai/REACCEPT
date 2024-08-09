  @CheckNoWriter @CheckNoStats
  @Test(dataProvider = "caches")
  @CacheSpec(removalListener = { Listener.DEFAULT, Listener.REJECTING })
  public void estimatedSize(Cache<Integer, Integer> cache, CacheContext context) {
    assertThat(cache.estimatedSize(), is(context.initialSize()));
  }
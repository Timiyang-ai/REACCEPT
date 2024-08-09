  @CheckNoWriter
  @CacheSpec(removalListener = { Listener.DEFAULT, Listener.REJECTING })
  @Test(dataProvider = "caches", expectedExceptions = NullPointerException.class)
  public void getIfPresent_nullKey(AsyncCache<Integer, Integer> cache, CacheContext context) {
    cache.getIfPresent(null);
  }
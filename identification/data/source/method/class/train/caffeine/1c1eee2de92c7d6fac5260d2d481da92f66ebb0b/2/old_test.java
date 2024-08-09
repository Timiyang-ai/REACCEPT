  @CheckNoWriter
  @CacheSpec(removalListener = { Listener.DEFAULT, Listener.REJECTING })
  @Test(dataProvider = "caches", expectedExceptions = NullPointerException.class)
  public void refresh_null(LoadingCache<Integer, Integer> cache, CacheContext context) {
    cache.refresh(null);
  }
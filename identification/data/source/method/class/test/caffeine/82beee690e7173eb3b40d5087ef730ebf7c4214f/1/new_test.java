  @CacheSpec
  @CheckNoWriter
  @Test(dataProvider = "caches", expectedExceptions = NullPointerException.class)
  public void get_null(AsyncLoadingCache<Integer, Integer> cache, CacheContext context) {
    cache.get(null);
  }
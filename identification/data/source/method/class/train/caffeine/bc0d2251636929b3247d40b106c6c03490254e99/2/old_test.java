  @CacheSpec
  @CheckNoWriter @CheckNoStats
  @Test(dataProvider = "caches", expectedExceptions = NullPointerException.class)
  public void get_nullKey(Cache<Integer, Integer> cache, CacheContext context) {
    cache.get(null, Function.identity());
  }
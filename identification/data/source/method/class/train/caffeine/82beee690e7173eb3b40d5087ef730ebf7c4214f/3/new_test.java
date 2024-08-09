  @CacheSpec
  @CheckNoWriter
  @Test(dataProvider = "caches", expectedExceptions = UnsupportedOperationException.class)
  public void getAll_immutable(AsyncLoadingCache<Integer, Integer> cache, CacheContext context) {
    cache.getAll(context.absentKeys()).join().clear();
  }
  @CacheSpec
  @Test(dataProvider = "caches", expectedExceptions = UnsupportedOperationException.class)
  public void getAll_immutable(LoadingCache<Integer, Integer> cache, CacheContext context) {
    cache.getAll(context.absentKeys()).clear();
  }
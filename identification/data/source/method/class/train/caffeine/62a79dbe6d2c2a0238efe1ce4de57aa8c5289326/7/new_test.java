  @Test(dataProvider = "caches")
  @CacheSpec(removalListener = { Listener.DEFAULT, Listener.REJECTING })
  public void put_insert(Cache<Integer, Integer> cache, CacheContext context) {
    cache.put(context.absentKey(), context.absentValue());
    assertThat(cache.estimatedSize(), is(context.initialSize() + 1));
    assertThat(cache.getIfPresent(context.absentKey()), is(context.absentValue()));
  }
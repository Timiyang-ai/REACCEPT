  @CheckNoWriter
  @Test(dataProvider = "caches")
  @CacheSpec(removalListener = { Listener.DEFAULT, Listener.REJECTING })
  public void getAllPresent_absent(Cache<Integer, Integer> cache, CacheContext context) {
    Map<Integer, Integer> result = cache.getAllPresent(context.absentKeys());
    assertThat(result.size(), is(0));

    int count = context.absentKeys().size();
    assertThat(context, both(hasMissCount(count)).and(hasHitCount(0)));
    assertThat(context, both(hasLoadSuccessCount(0)).and(hasLoadFailureCount(0)));
  }
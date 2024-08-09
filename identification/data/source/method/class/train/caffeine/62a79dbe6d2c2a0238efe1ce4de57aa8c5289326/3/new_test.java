  @Test(dataProvider = "caches")
  @CacheSpec(removalListener = { Listener.DEFAULT, Listener.REJECTING })
  public void putAll_insert(Cache<Integer, Integer> cache, CacheContext context) {
    int startKey = context.original().size() + 1;
    Map<Integer, Integer> entries = IntStream
        .range(startKey, 100 + startKey).boxed()
        .collect(Collectors.toMap(Function.identity(), key -> -key));
    cache.putAll(entries);
    assertThat(cache.estimatedSize(), is(100 + context.initialSize()));

    verifyWriter(context, (verifier, writer) -> {
      verifier.wroteAll(entries);
    });
  }
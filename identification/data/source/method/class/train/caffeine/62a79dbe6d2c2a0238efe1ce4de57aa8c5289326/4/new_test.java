  @CacheSpec
  @Test(dataProvider = "caches")
  public void invalidateAll(Cache<Integer, Integer> cache, CacheContext context) {
    cache.invalidateAll();
    assertThat(cache.estimatedSize(), is(0L));
    assertThat(cache, hasRemovalNotifications(context,
        context.original().size(), RemovalCause.EXPLICIT));
    verifyWriter(context, (verifier, writer) -> {
      verifier.deletedAll(context.original(), RemovalCause.EXPLICIT);
    });
  }
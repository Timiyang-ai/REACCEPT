  @Test(dataProvider = "caches")
  @CacheSpec(compute = Compute.SYNC, implementation = Implementation.Caffeine,
      population = Population.EMPTY, maximumSize = Maximum.ONE)
  public void evict_alreadyRemoved(Cache<Integer, Integer> cache, CacheContext context) {
    BoundedLocalCache<Integer, Integer> localCache = asBoundedLocalCache(cache);
    Entry<Integer, Integer> oldEntry = Iterables.get(context.absent().entrySet(), 0);
    Entry<Integer, Integer> newEntry = Iterables.get(context.absent().entrySet(), 1);

    localCache.put(oldEntry.getKey(), oldEntry.getValue());
    localCache.evictionLock.lock();
    try {
      Object lookupKey = localCache.nodeFactory.newLookupKey(oldEntry.getKey());
      Node<Integer, Integer> node = localCache.data.get(lookupKey);
      checkStatus(node, Status.ALIVE);
      ConcurrentTestHarness.execute(() -> {
        localCache.put(newEntry.getKey(), newEntry.getValue());
        assertThat(localCache.remove(oldEntry.getKey()), is(oldEntry.getValue()));
      });
      await().until(() -> localCache.containsKey(oldEntry.getKey()), is(false));
      await().until(() -> {
        synchronized (node) {
          return !node.isAlive();
        }
      });
      checkStatus(node, Status.RETIRED);
      localCache.cleanUp();

      checkStatus(node, Status.DEAD);
      assertThat(localCache.containsKey(newEntry.getKey()), is(true));
      await().until(() -> cache, hasRemovalNotifications(context, 1, RemovalCause.EXPLICIT));
    } finally {
      localCache.evictionLock.unlock();
    }
  }
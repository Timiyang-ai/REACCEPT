@Test
  public void maximizeConcurrency() {
    Cache<Integer, Integer> c = Cache2kBuilder.of(Integer.class, Integer.class)
      .boostConcurrency(true)
      .expireAfterWrite(5, TimeUnit.MINUTES)
      .loader(new CacheLoader<Integer, Integer>() {
        @Override
        public Integer load(final Integer key) throws Exception {
          return key + 123;
        }
      })
      .build();
    int _timerMask =
      ((TimingHandler.Static) c.requestInterface(HeapCache.class).timing).timerMask;
    if (Runtime.getRuntime().availableProcessors() >1) {
      assertTrue(_timerMask > 0);
    }
    c.close();
  }
  @Test
  public void register_noListener() {
    MutableCacheEntryListenerConfiguration<Integer, Integer> configuration =
        new MutableCacheEntryListenerConfiguration<>(null, null, false, false);
    dispatcher.register(configuration);
    assertThat(dispatcher.dispatchQueues.keySet(), is(empty()));
  }
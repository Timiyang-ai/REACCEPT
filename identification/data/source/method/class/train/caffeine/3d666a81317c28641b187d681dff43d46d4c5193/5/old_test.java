  @Test
  public void deregister() {
    MutableCacheEntryListenerConfiguration<Integer, Integer> configuration =
        new MutableCacheEntryListenerConfiguration<>(() -> createdListener, null, false, false);
    dispatcher.register(configuration);
    dispatcher.deregister(configuration);
    assertThat(dispatcher.dispatchQueues.keySet(), is(empty()));
  }
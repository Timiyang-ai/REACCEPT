  @Test
  public void scheduleDrainBuffers() {
    Executor executor = Mockito.mock(Executor.class);
    BoundedLocalCache<?, ?> cache = new BoundedLocalCache<Object, Object>(
        Caffeine.newBuilder().executor(executor), /* loader */ null, /* async */ false) {};
    Map<Integer, Integer> transitions = ImmutableMap.of(
        IDLE, PROCESSING_TO_IDLE,
        REQUIRED, PROCESSING_TO_IDLE,
        PROCESSING_TO_IDLE, PROCESSING_TO_IDLE,
        PROCESSING_TO_REQUIRED, PROCESSING_TO_REQUIRED);
    transitions.forEach((start, end) -> {
      cache.drainStatus = start;
      cache.scheduleDrainBuffers();
      assertThat(cache.drainStatus, is(end));

      if (!start.equals(end)) {
        Mockito.verify(executor).execute(any());
        Mockito.reset(executor);
      }
    });
  }
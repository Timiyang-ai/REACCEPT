  @Test
  public void publishCreated() {
    registerAll();
    dispatcher.publishCreated(cache, 1, 2);
    verify(createdListener, times(4)).onCreated(any());
    assertThat(EventDispatcher.pending.get(), hasSize(2));
  }
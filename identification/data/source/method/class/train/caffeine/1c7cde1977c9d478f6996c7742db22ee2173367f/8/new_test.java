  @Test
  public void publishUpdated() {
    registerAll();
    dispatcher.publishUpdated(cache, 1, 2, 3);
    verify(updatedListener, times(4)).onUpdated(any());
    assertThat(EventDispatcher.pending.get(), hasSize(2));
  }
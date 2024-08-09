  @Test
  public void publishRemoved() {
    registerAll();
    dispatcher.publishRemoved(cache, 1, 2);
    verify(removedListener, times(4)).onRemoved(any());
    assertThat(EventDispatcher.pending.get(), hasSize(2));
  }
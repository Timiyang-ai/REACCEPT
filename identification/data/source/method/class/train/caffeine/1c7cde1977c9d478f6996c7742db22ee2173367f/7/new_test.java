  @Test
  public void publishExpired() {
    registerAll();
    dispatcher.publishExpired(cache, 1, 2);
    verify(expiredListener, times(4)).onExpired(any());
    assertThat(EventDispatcher.pending.get(), hasSize(2));
  }
  @Test
  public void awaitSynchronous() {
    EventDispatcher.pending.get().add(CompletableFuture.completedFuture(null));
    dispatcher.awaitSynchronous();
    assertThat(EventDispatcher.pending.get(), is(empty()));
  }
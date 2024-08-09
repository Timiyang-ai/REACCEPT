  @Test public void shutdown() {
    cache.set("key", makeBitmap(1, 1));
    assertThat(cache.size()).isEqualTo(1);
    picasso.shutdown();
    assertThat(cache.size()).isEqualTo(0);
    assertThat(eventRecorder.closed).isTrue();
    verify(dispatcher).shutdown();
    assertThat(picasso.shutdown).isTrue();
  }
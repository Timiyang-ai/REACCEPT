  @Test public void handleSend_defaultsToMakeNewTrace() {
    when(sampler.trySample(any(FromHttpAdapter.class))).thenReturn(null);

    assertThat(handler.handleSend(injector, request))
      .extracting(brave.Span::isNoop, s -> s.context().parentId())
      .containsExactly(false, null);
  }
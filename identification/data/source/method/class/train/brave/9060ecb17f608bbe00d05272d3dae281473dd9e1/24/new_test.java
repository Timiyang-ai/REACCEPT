  @Test public void handleReceive_oldHandler() {
    when(sampler.trySample(any(FromHttpAdapter.class))).thenReturn(null);

    brave.Span span = handler.handleSend(injector, adapter, request);
    handler.handleReceive(response, null, span);

    verify(parser).response(eq(adapter), eq(response), isNull(), any(SpanCustomizer.class));
  }
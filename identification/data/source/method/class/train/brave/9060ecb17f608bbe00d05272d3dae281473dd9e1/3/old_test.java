  @Test public void handleSend_oldHandler() {
    when(extractor.extract(request)).thenReturn(TraceContextOrSamplingFlags.EMPTY);
    when(sampler.trySample(any(FromHttpAdapter.class))).thenReturn(null);

    brave.Span span = handler.handleReceive(extractor, request);
    handler.handleSend(response, null, span);

    verify(parser).response(eq(adapter), eq(response), isNull(), any(SpanCustomizer.class));
  }
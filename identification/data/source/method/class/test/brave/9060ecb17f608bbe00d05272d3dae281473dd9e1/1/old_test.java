  @Test public void handleReceive_defaultRequest() {
    // request sampler abstains (trace ID sampler will say true)
    when(requestSampler.trySample(defaultRequest)).thenReturn(null);

    Span newSpan = defaultHandler.handleReceive(defaultRequest);
    assertThat(newSpan.isNoop()).isFalse();
    assertThat(newSpan.context().shared()).isFalse();
  }
  @Test public void withSpanInScope() {
    Span current = tracer.newTrace();

    try (SpanInScope ws = tracer.withSpanInScope(current)) {
      assertThat(tracer.currentSpan())
        .isEqualTo(current);
      assertThat(tracer.currentSpanCustomizer())
        .isNotEqualTo(current)
        .isNotEqualTo(NoopSpanCustomizer.INSTANCE);
    }

    // context was cleared
    assertThat(tracer.currentSpan()).isNull();
  }
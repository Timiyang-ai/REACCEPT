  @Test public void equals_sameContext() {
    Span current1, current2;
    try (Scope ws = tracing.currentTraceContext().newScope(context)) {
      current1 = tracing.tracer().currentSpan();
      current2 = tracing.tracer().currentSpan();
    }

    assertThat(current1)
      .isInstanceOf(LazySpan.class)
      .isNotSameAs(current2)
      .isEqualTo(current2);
  }
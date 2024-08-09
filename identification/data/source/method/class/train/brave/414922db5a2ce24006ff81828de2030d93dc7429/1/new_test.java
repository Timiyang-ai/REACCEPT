  @Test public void tag() {
    span.start();
    try (Tracer.SpanInScope ws = tracing.tracer().withSpanInScope(span)) {
      spanCustomizer.tag("foo", "bar");
    }
    span.flush();

    assertThat(spans).flatExtracting(s -> s.tags().entrySet())
      .containsExactly(entry("foo", "bar"));
  }
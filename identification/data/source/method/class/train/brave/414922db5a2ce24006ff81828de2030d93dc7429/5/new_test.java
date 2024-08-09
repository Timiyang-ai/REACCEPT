  @Test public void annotate() {
    span.start();
    try (Tracer.SpanInScope ws = tracing.tracer().withSpanInScope(span)) {
      spanCustomizer.annotate("foo");
    }
    span.flush();

    assertThat(spans).flatExtracting(zipkin2.Span::annotations)
      .extracting(Annotation::value)
      .containsExactly("foo");
  }
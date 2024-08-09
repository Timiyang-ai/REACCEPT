  @Test public void name() {
    span.start();
    try (Tracer.SpanInScope ws = tracing.tracer().withSpanInScope(span)) {
      spanCustomizer.name("newname");
    }
    span.flush();

    assertThat(spans).extracting(zipkin2.Span::name)
      .containsExactly("newname");
  }
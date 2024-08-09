  @Test public void toSpan() {
    TraceContext context = tracer.newTrace().context();

    assertThat(tracer.toSpan(context))
      .isInstanceOf(RealSpan.class)
      .extracting(Span::context)
      .isEqualTo(context);
  }
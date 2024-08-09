  @Test public void reporter_hasNiceToString() {
    tracer = Tracing.newBuilder().build().tracer();

    assertThat(tracer.finishedSpanHandler)
      .hasToString("LoggingReporter{name=brave.Tracer}");
  }
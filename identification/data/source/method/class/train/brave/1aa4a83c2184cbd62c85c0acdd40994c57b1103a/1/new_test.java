  @Test public void current_get() {
    TraceContext context = extractWithAmazonTraceId();

    try (Tracing t = Tracing.newBuilder().propagationFactory(factory).build();
         CurrentTraceContext.Scope scope = t.currentTraceContext().newScope(context)) {
      assertThat(ExtraFieldPropagation.get("x-amzn-trace-id"))
        .isEqualTo(awsTraceId);
    }
  }
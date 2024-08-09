  @Test public void getAll() {
    TraceContext context = extractWithAmazonTraceId();

    assertThat(ExtraFieldPropagation.getAll(context))
      .hasSize(1)
      .containsEntry("x-amzn-trace-id", awsTraceId);
  }
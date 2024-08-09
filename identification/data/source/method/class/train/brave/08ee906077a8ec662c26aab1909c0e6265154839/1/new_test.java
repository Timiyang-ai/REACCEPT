  @Test public void traceIdString_caches() {
    TraceContext context = TraceContext.newBuilder().traceId(1L).spanId(2L).build();

    assertThat(context.traceIdString).isNull();
    assertThat(context.traceIdString())
      .isNotNull()
      .isEqualTo("0000000000000001");
    assertThat(context.traceIdString)
      .isEqualTo("0000000000000001");
  }
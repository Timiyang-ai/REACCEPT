  @Test public void writeB3SingleFormat_notYetSampled() {
    TraceContext context = TraceContext.newBuilder().traceId(1).spanId(3).build();

    assertThat(writeB3SingleFormat(context))
      .isEqualTo(traceId + "-" + spanId)
      .isEqualTo(new String(writeB3SingleFormatAsBytes(context), UTF_8));
  }
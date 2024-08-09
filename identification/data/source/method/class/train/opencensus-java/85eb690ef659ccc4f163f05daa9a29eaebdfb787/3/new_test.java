  @Test
  public void isValid() {
    assertThat(SpanContext.INVALID.isValid()).isFalse();
    assertThat(
            SpanContext.create(
                    TraceId.fromBytes(firstTraceIdBytes), SpanId.INVALID, TraceOptions.DEFAULT)
                .isValid())
        .isFalse();
    assertThat(
            SpanContext.create(
                    TraceId.INVALID, SpanId.fromBytes(firstSpanIdBytes), TraceOptions.DEFAULT)
                .isValid())
        .isFalse();
    assertThat(first.isValid()).isTrue();
    assertThat(second.isValid()).isTrue();
  }
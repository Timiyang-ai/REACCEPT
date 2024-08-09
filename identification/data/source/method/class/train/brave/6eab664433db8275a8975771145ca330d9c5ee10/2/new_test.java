  @Test public void parseB3SingleFormat_largest() {
    assertThat(
      parseB3SingleFormat(traceIdHigh + traceId + "-" + spanId + "-1-" + parentId)
    ).extracting(TraceContextOrSamplingFlags::context).isEqualToComparingFieldByField(
      TraceContext.newBuilder()
        .traceIdHigh(9)
        .traceId(1)
        .parentId(2)
        .spanId(3)
        .sampled(true)
        .build()
    );
  }
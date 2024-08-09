@Test public void extract_skipsSelfField() {
    // TODO: check with AWS if it is valid to have arbitrary fields in front of standard ones.
    // we currently permit them
    carrier.put("x-amzn-trace-id", "Robot=Hello;Self=1-582113d1-1e48b74b3603af8479078ed6;  " +
        "Root=1-58211399-36d228ad5d99923122bbe354;  " +
        "TotalTimeSoFar=112ms;CalledFrom=Foo");

    TraceContextOrSamplingFlags extracted = extractor.extract(carrier);
    assertThat(extracted.traceIdContext())
        .isEqualTo(TraceIdContext.newBuilder()
            .traceIdHigh(lowerHexToUnsignedLong("5821139936d228ad"))
            .traceId(lowerHexToUnsignedLong("5d99923122bbe354"))
            .build());

    assertThat(((AWSPropagation.Extra) extracted.extra().get(0)).fields)
        .contains(new StringBuilder(";Robot=Hello;TotalTimeSoFar=112ms;CalledFrom=Foo"));
  }
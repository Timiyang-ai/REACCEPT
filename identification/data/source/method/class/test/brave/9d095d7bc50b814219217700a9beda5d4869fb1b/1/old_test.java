  @Test public void parseSpanId() {
    String spanIdString = "48485a3953bb6124";

    TraceContext.Builder builder = parseGoodSpanId(spanIdString);

    assertThat(HexCodec.toLowerHex(builder.spanId))
      .isEqualTo(spanIdString);
  }
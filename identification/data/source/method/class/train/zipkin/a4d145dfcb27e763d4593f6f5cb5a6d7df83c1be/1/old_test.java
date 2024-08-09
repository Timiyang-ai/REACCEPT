  @Test
  public void decoderForMessage_thrift() {
    byte[] message = SpanBytesEncoder.THRIFT.encode(span1);
    assertThat(SpanBytesDecoderDetector.decoderForMessage(message))
        .isEqualTo(SpanBytesDecoder.THRIFT);
  }
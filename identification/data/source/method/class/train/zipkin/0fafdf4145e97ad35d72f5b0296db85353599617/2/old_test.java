  @Test
  public void decoderForListMessage_thrift() {
    byte[] message = SpanBytesEncoder.THRIFT.encodeList(asList(span1, span2));
    assertThat(SpanBytesDecoderDetector.decoderForListMessage(message))
        .isEqualTo(SpanBytesDecoder.THRIFT);
  }
public static BytesDecoder<Span> decoderForMessage(byte[] span) {
    BytesDecoder<Span> decoder = detectDecoder(span);
    if (span[0] == 12 /* List[ThriftSpan] */ || span[0] == '[') {
      throw new IllegalArgumentException("Expected json or thrift object, not list encoding");
    }
    if (decoder == SpanBytesDecoder.JSON_V2 || decoder == SpanBytesDecoder.PROTO3) {
      throw new UnsupportedOperationException("v2 formats should only be used with list messages");
    }
    return decoder;
  }
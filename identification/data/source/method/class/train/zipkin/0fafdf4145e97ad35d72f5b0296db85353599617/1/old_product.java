public static BytesDecoder<Span> decoderForListMessage(byte[] spans) {
    BytesDecoder<Span> decoder = detectDecoder(spans);
    if (spans[0] != 12 /* List[ThriftSpan] */ && !protobuf3(spans) && spans[0] != '[') {
      throw new IllegalArgumentException("Expected json, proto3 or thrift list encoding");
    }
    return decoder;
  }
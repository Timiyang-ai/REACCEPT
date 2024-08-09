public static BytesDecoder<Span> decoderForListMessage(byte[] spans) {
    return decoderForListMessage(ByteBuffer.wrap(spans));
  }
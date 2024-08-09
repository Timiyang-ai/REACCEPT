public static ByteBuffer sliceByteBuffer(ByteBuffer buffer, int position, int length) {
    return (ByteBuffer) buffer.duplicate().position(position).limit(position + length);
  }
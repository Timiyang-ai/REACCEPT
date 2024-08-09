public static ByteBuffer sliceByteBuffer(ByteBuffer buffer, int position, int length) {
    ByteBuffer slicedBuffer = ((ByteBuffer) buffer.duplicate().position(position)).slice();
    slicedBuffer.limit(length);
    return slicedBuffer;
  }
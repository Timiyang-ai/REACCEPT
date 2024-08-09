public static ByteBuffer cloneByteBuffer(ByteBuffer buf) {
    ByteBuffer ret = ByteBuffer.allocate(buf.limit() - buf.position());
    if (buf.hasArray()) {
      ret.put(buf.array(), buf.position(), buf.limit() - buf.position());
    } else {
      // direct buffer
      ret.put(buf);
    }
    ret.flip();
    return ret;
  }
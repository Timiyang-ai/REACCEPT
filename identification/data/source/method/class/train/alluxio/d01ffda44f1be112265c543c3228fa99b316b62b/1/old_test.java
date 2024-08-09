  @Test
  public void cloneByteBuffer() {
    final int bufferSize = 10;
    ByteBuffer buf = ByteBuffer.allocate(bufferSize);
    for (byte i = 0; i < bufferSize; i++) {
      buf.put(i);
    }
    ByteBuffer bufClone = BufferUtils.cloneByteBuffer(buf);
    assertEquals(buf, bufClone);
  }
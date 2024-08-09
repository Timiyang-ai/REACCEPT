  @Test
  public void cleanDirectBuffer() {
    final int MAX_ITERATIONS = 1024;
    final int BUFFER_SIZE = 16 * 1024 * 1024;
    // bufferArray keeps reference to each buffer to avoid auto GC
    ByteBuffer[] bufferArray = new ByteBuffer[MAX_ITERATIONS];
    try {
      for (int i = 0; i < MAX_ITERATIONS; i++) {
        ByteBuffer buf = ByteBuffer.allocateDirect(BUFFER_SIZE);
        bufferArray[i] = buf;
        BufferUtils.cleanDirectBuffer(buf);
      }
    } catch (OutOfMemoryError ooe) {
      fail("cleanDirectBuffer is causing memory leak." + ooe.getMessage());
    }
  }
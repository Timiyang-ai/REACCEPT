public void write(long pos, Buffer source, long byteCount) throws IOException {
    if (byteCount < 0 || byteCount > source.size()) throw new IndexOutOfBoundsException();

    while (byteCount > 0L) {
      try {
        // Write bytes to the byte[], and tell the ByteBuffer wrapper about 'em.
        int toWrite = (int) Math.min(BUFFER_SIZE, byteCount);
        source.read(byteArray, 0, toWrite);
        byteBuffer.limit(toWrite);

        // Copy bytes from the ByteBuffer to the file.
        do {
          int bytesWritten = fileChannel.write(byteBuffer, pos);
          pos += bytesWritten;
        } while (byteBuffer.hasRemaining());

        byteCount -= toWrite;
      } finally {
        byteBuffer.clear();
      }
    }
  }
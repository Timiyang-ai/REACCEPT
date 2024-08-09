public void read(long pos, Buffer sink, long byteCount) throws IOException {
    if (byteCount < 0) throw new IndexOutOfBoundsException();

    while (byteCount > 0L) {
      try {
        // Read up to byteCount bytes.
        byteBuffer.limit((int) Math.min(BUFFER_SIZE, byteCount));
        if (fileChannel.read(byteBuffer, pos) == -1) throw new EOFException();
        int bytesRead = byteBuffer.position();

        // Write those bytes to sink.
        sink.write(byteArray, 0, bytesRead);
        pos += bytesRead;
        byteCount -= bytesRead;
      } finally {
        byteBuffer.clear();
      }
    }
  }
public void write(long pos, Buffer source, long byteCount) throws IOException {
    if (byteCount < 0 || byteCount > source.size()) throw new IndexOutOfBoundsException();

    while (byteCount > 0L) {
      long bytesWritten = fileChannel.transferFrom(source, pos, byteCount);
      pos += bytesWritten;
      byteCount -= bytesWritten;
    }
  }
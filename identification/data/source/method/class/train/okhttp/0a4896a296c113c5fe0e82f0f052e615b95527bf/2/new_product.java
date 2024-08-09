public void read(long pos, Buffer sink, long byteCount) throws IOException {
    if (byteCount < 0) throw new IndexOutOfBoundsException();

    while (byteCount > 0L) {
      long bytesRead = fileChannel.transferTo(pos, byteCount, sink);
      pos += bytesRead;
      byteCount -= bytesRead;
    }
  }
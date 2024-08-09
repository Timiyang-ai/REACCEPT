  private void writeHeader(LogSegment segment, byte[] buf) throws IOException {
    FileChannel channel = segment.getView().getSecond();
    ByteBuffer buffer = ByteBuffer.wrap(buf);
    while (buffer.hasRemaining()) {
      channel.write(buffer, 0);
    }
  }
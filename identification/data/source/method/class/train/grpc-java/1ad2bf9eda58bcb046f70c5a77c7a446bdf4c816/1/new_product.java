private int writeKnownLength(InputStream message, int messageLength, boolean compressed)
      throws IOException {
    ByteBuffer header = ByteBuffer.wrap(headerScratch);
    header.put(compressed ? COMPRESSED : UNCOMPRESSED);
    header.putInt(messageLength);
    // Allocate the initial buffer chunk based on frame header + payload length.
    // Note that the allocator may allocate a buffer larger or smaller than this length
    if (buffer == null) {
      buffer = bufferAllocator.allocate(header.position() + messageLength);
    }
    writeRaw(headerScratch, 0, header.position());
    return writeToOutputStream(message, outputStreamAdapter);
  }
private void writeHeader(long capacityInBytes) throws IOException {
    Crc32 crc = new Crc32();
    ByteBuffer buffer = ByteBuffer.allocate(HEADER_SIZE);
    buffer.putShort(VERSION);
    buffer.putLong(capacityInBytes);
    crc.update(buffer.array(), 0, HEADER_SIZE - CRC_SIZE);
    buffer.putLong(crc.getValue());
    buffer.flip();
    appendFrom(Channels.newChannel(new ByteBufferInputStream(buffer)), buffer.remaining());
  }
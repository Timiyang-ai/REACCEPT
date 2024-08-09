    public static long updateByteBuffer(ByteBuffer buffer) {
        CRC32 crc = new CRC32();
        buffer.rewind();
        crc.update(buffer);
        return crc.getValue();
    }
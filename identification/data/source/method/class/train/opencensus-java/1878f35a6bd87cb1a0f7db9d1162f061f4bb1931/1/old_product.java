static void longToBase16String(long value, StringBuilder dest) {
    byteToBase16((byte) (value >> 56 & 0xFFL), dest);
    byteToBase16((byte) (value >> 48 & 0xFFL), dest);
    byteToBase16((byte) (value >> 40 & 0xFFL), dest);
    byteToBase16((byte) (value >> 32 & 0xFFL), dest);
    byteToBase16((byte) (value >> 24 & 0xFFL), dest);
    byteToBase16((byte) (value >> 16 & 0xFFL), dest);
    byteToBase16((byte) (value >> 8 & 0xFFL), dest);
    byteToBase16((byte) (value & 0xFFL), dest);
  }
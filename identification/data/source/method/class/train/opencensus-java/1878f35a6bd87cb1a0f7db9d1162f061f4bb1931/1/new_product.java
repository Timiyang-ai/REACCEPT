static void longToBase16String(long value, char[] dest, int destOffset) {
    byteToBase16((byte) (value >> 56 & 0xFFL), dest, destOffset);
    byteToBase16((byte) (value >> 48 & 0xFFL), dest, destOffset + BYTE_BASE16);
    byteToBase16((byte) (value >> 40 & 0xFFL), dest, destOffset + 2 * BYTE_BASE16);
    byteToBase16((byte) (value >> 32 & 0xFFL), dest, destOffset + 3 * BYTE_BASE16);
    byteToBase16((byte) (value >> 24 & 0xFFL), dest, destOffset + 4 * BYTE_BASE16);
    byteToBase16((byte) (value >> 16 & 0xFFL), dest, destOffset + 5 * BYTE_BASE16);
    byteToBase16((byte) (value >> 8 & 0xFFL), dest, destOffset + 6 * BYTE_BASE16);
    byteToBase16((byte) (value & 0xFFL), dest, destOffset + 7 * BYTE_BASE16);
  }
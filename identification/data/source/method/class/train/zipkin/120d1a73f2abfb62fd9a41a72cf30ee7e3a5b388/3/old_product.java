void writeVarint(int v) {
    while ((v & ~0x7f) != 0) {
      buf[pos++] = (byte) ((v & 0x7f) | 0x80);
      v >>>= 7;
    }
    buf[pos++] = (byte) v;
  }
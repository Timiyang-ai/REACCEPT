boolean increment(int i, int j) {
    int offset = j << 2;
    long mask = (0xfL << offset);
    long byteOffset = byteOffset(i);
    long slot = UnsafeAccess.UNSAFE.getLong(table, byteOffset);
    if ((slot & mask) != mask) {
      table[i] = slot + (1L << offset);
      return true;
    }
    return false;
  }